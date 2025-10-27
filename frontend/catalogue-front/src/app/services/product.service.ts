// src/app/services/product.service.ts
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Produit } from '../models/produit';

@Injectable({
  providedIn: 'root'
})
export class ProductService {
  private apiUrl = 'http://localhost:8081/api/product'; // URL de base alignée avec le contrôleur

  constructor(private http: HttpClient) { }

  // 🔹 Récupérer tous les produits
  getProduits(): Observable<Produit[]> {
    return this.http.get<Produit[]>(`${this.apiUrl}/all`);
  }

  // 🔹 Récupérer les produits d'une catégorie donnée
  getProduitsByCategorie(id: string): Observable<Produit[]> {
    return this.http.get<Produit[]>(`${this.apiUrl}/cat/${id}`);
  }

  // 🔹 Rechercher des produits par nom
  searchProduits(search: string): Observable<Produit[]> {
    return this.http.get<Produit[]>(`${this.apiUrl}/search`, {
      params: { search }
    });
  }

  // 🔹 Ajouter un produit avec image
  addProduit(produit: Produit, imageFile?: File): Observable<void> {
    const formData = new FormData();
    formData.append('produit', JSON.stringify(produit));
    if (imageFile) {
      formData.append('file', imageFile);
    }
    return this.http.post<void>(`${this.apiUrl}/add`, formData);
  }

  // 🔹 Mettre à jour un produit
  updateProduit(produit: Produit): Observable<void> {
    return this.http.put<void>(`${this.apiUrl}/update`, produit);
  }

  // 🔹 Supprimer un produit
  deleteProduit(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/delete/${id}`);
  }

  // 🔹 Obtenir l'URL de l'image d'un produit
  getImageUrl(id: number): string {
    return `${this.apiUrl}/image/${id}`;
  }
}
