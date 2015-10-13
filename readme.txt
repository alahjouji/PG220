1)Pour le lancement de l'application elle se fait à partir du répertoire bin avec la commande:

java gestion.Devis commandes.xml fournisseurs.xml

"les fichiers commandes.xml et fournisseurs.xml sont placées dans le répertoire bin"

2)Les fichiers resultats sont générées dans un dossier "generated" qui est dans le repertoire bin

3)une troisième méthode plus efficace que les 2 autres a été implémenté (voir efficacité avec les fichiers svg en lancant l'application avec les fichiers commandes.xml et fournisseurs.xml proposés)

4)lancement des classes de test à partir du répertoire bin:

java gestion.TestEcritureXML

java gestion.TestLectureCommandes commandes.xml

java gestion.TestLectureFournisseurs fournisseurs.xml

java gestion.TestRejet commandes.xml fournisseurs.xml

java gestion.TestMethode1 commandes.xml fournisseurs.xml

java gestion.TestMethode2 commandes.xml fournisseurs.xml

java gestion.TestMethode3 commandes.xml fournisseurs.xml
