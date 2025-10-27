import { Component, OnInit } from '@angular/core';
import {FormsModule, NgForm} from '@angular/forms';
import { Router } from '@angular/router';
import { ProductService } from '../../../services/product.service';
import { CategorieService } from '../../../services/categorie.service';
import {CommonModule} from "@angular/common";

@Component({
  selector: 'app-add-product',
  standalone: true, // Uncomment for standalone mode
  imports: [CommonModule, FormsModule], // Uncomment for standalone mode
  templateUrl: './add-product.component.html',
  styleUrls: ['./add-product.component.css']
})
export class AddProductComponent implements OnInit {

  categories: any[] = []; // Loaded from CategorieService
  selectedFile: File | null = null; // Selected image file
  imagePreview: string | null = null; // Image preview URL

  constructor(
    private prodService: ProductService,
    private router: Router,
    private catService: CategorieService
  ) {}

  ngOnInit(): void {
    this.loadCategories();
  }

  // Load all categories
  private loadCategories(): void {
    this.catService.getCategories().subscribe(data => this.categories = data);
  }

  // Handle file selection
  onFileSelected(event: any): void {
    const file = event.target.files[0];
    if (file) {
      this.selectedFile = file;
      
      // Create preview
      const reader = new FileReader();
      reader.onload = (e: any) => {
        this.imagePreview = e.target.result;
      };
      reader.readAsDataURL(file);
    }
  }

  // Remove selected image
  removeImage(): void {
    this.selectedFile = null;
    this.imagePreview = null;
  }

  // Submit the product form
  onSubmit(productForm: NgForm): void {
    if (productForm.invalid) {
      return; // Do not submit if the form is invalid
    }

    // The form value is directly the Produit object
    this.prodService.addProduit(productForm.value, this.selectedFile || undefined).subscribe(() => {
      alert("Produit ajouté avec succès !");
      this.router.navigate(['/products']);
    });
  }

  // Navigate back to the product list
  retourListe(): void {
    this.router.navigate(['/products']);
  }
}
