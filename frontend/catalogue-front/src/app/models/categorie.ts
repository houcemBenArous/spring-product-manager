import {Produit} from "./produit";

export interface Categorie {
  id: string;
  nom: string;
  produits?: Produit[]; // optional to avoid circular issues in UI
}
