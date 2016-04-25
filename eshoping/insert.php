<?php



$bdd = new PDO('mysql:host=localhost;dbname=eshopping;charset=utf8', 'root', '');
$bdd->query("INSERT INTO `produit`( `marque`, `prix`, `caracteristique`, `adresse`, `categorie`)VALUES('".$_POST['marque']."','".$_POST['prix']."','".$_POST['caracteristique']."','".$_POST['adresse']."','".$_POST['categorie']."')");
//$bdd->query("INSERT INTO `produit`(`id`, `marque`, `prix`, `caracteristique`, `adresse`, `categorie`) VALUES('marque','prix','carac','adress','cat')");
?>