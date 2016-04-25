<?php


$bdd = new PDO('mysql:host=localhost;dbname=eshopping;charset=utf8', 'root', '');
$req=$bdd->query("select * from produit");
$reponse=array();
while ($donnees = $req->fetch())
{
//echo $donnees['texte'];
    
array_push($reponse, array("marque"=>$donnees['marque'],"prix"=>$donnees['prix'],"caracteristique"=>$donnees['caracteristique'],"adresse"=>$donnees['adresse'],"categorie"=>$donnees['categorie']))   ;
    
}


echo json_encode(array("server_response"=>$reponse));




?>