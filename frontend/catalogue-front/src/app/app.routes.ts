import { Routes } from '@angular/router';
import { ProductsComponent } from './components/products/products.component';
import { AddProductComponent } from './components/products/add-product/add-product.component';

export const routes: Routes = [
    {path: 'products', component: ProductsComponent},
    {path: 'add-product', component: AddProductComponent},
    {path: '', redirectTo: 'products', pathMatch: 'full'}
];
