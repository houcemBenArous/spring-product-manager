import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ProductService } from '../../services/product.service';
import { CategorieService } from '../../services/categorie.service';
import { Produit } from '../../models/produit';
import {CommonModule} from "@angular/common";
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-products',
  standalone: true, // Uncomment for standalone mode
  imports: [CommonModule, FormsModule], // Uncomment for standalone mode
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.css']
})
export class ProductsComponent implements OnInit {

  produits?: Produit[];
  searchNom: string = '';
  selectedCategorie: string = ''; // Category ID
  categories: any[] = []; // Loaded from CategorieService

  constructor(
    private serviceProduit: ProductService,
    private serviceCategorie: CategorieService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.loadAllProducts();
    this.loadCategories();
  }

  // Load all products
  loadAllProducts(): void {
    this.serviceProduit.getProduits().subscribe(data => this.produits = data);
  }

  // Load all categories
  loadCategories(): void {
    this.serviceCategorie.getCategories().subscribe(data => this.categories = data);
  }

  // Search products by name
  onSearchChange(): void {
    if (this.searchNom) {
      this.serviceProduit.searchProduits(this.searchNom)
        .subscribe(data => this.produits = data);
    } else {
      this.loadAllProducts();
    }
  }

  // Filter products by category
  onCategorieChange(): void {
    if (this.selectedCategorie) {
      this.serviceProduit.getProduitsByCategorie(this.selectedCategorie)
        .subscribe(data => this.produits = data);
    } else {
      this.loadAllProducts();
    }
  }

  // Delete a product
  supprimer(p: Produit): void {
    const conf = confirm("Êtes-vous sûr de vouloir supprimer ce produit ?");
    if (!conf) return;

    if (p.id !== undefined) {
      this.serviceProduit.deleteProduit(p.id).subscribe({
        next: () => {
          // Remove from local array instead of reloading
          this.produits = this.produits?.filter(prod => prod.id !== p.id);
          alert("Produit supprimé avec succès !");
        },
        error: (err) => {
          console.error('Erreur lors de la suppression:', err);
          alert("Erreur lors de la suppression du produit.");
        }
      });
    }
  }

  // Navigate to add product page
  ajouterNouveauProduit(): void {
    this.router.navigate(['/add-product']);
  }

  // Get product image URL
  getProductImageUrl(product: Produit): string | null {
    if (product.id && product.photo) {
      console.log(`Product ${product.id} has photo: ${product.photo}`);
      return this.serviceProduit.getImageUrl(product.id);
    }
    console.log(`Product ${product.id} has no photo field`);
    return null;
  }

  // Handle image loading errors
  onImageError(event: any): void {
    console.error('Failed to load image:', event.target.src);
    event.target.style.display = 'none';
  }
}
