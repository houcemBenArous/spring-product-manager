// src/app/services/category.service.ts
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Categorie } from '../models/categorie';

@Injectable({
  providedIn: 'root'
})
export class CategorieService {
  private apiUrl = 'http://localhost:8081/api/category'; // Base URL correspondant au contrôleur

  constructor(private http: HttpClient) { }

  // 🔹 Récupérer toutes les catégories
  getCategories(): Observable<Categorie[]> {
    return this.http.get<Categorie[]>(`${this.apiUrl}/all`);
  }

  // 🔹 Récupérer une catégorie par ID
  getCategoryById(id: string): Observable<Categorie> {
    return this.http.get<Categorie>(`${this.apiUrl}/${id}`);
  }

  // 🔹 Ajouter une catégorie
  addCategory(categorie: Categorie): Observable<void> {
    return this.http.post<void>(`${this.apiUrl}/add`, categorie);
  }

  // 🔹 Mettre à jour une catégorie
  updateCategory(categorie: Categorie): Observable<void> {
    return this.http.put<void>(`${this.apiUrl}/update`, categorie);
  }

  // 🔹 Supprimer une catégorie (le backend attend un objet dans le corps de la requête)
  deleteCategory(categorie: Categorie): Observable<void> {
    return this.http.request<void>('delete', `${this.apiUrl}/delete`, {
      body: categorie
    });
  }
}
