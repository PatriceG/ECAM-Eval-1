package eval;


import java.util.Arrays;
import java.util.List;

//modifier le code pour qu'il compile et fonctionne 
//(vous pouvez modifier les propriétés du projet, les imports, les clauses throws mais PAS les clauses Catch!)

public class Eval {
   /**
    * Retourne le nom de la première ville trouvée avec le code postal spécifié
    * NE PAS MODIFIER le corps de cette méthode (mais la déclaration de la méthode est certainement à modifier pour que ça compile :) )
    * @param codePostal
    * @return
    */
	public String getVille(String codePostal) {
		String ville = null;
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/codepost", "eleve", "eleve");
		PreparedStatement prepStmt = conn.prepareStatement("SELECT * from villes where code_postal=?");
		prepStmt.setString(1, codePostal);
		ResultSet rs = prepStmt.executeQuery();
		if (rs.next()) {
			ville = rs.getString("nom");
		}
		return ville;
	}

	/**
	 * Détermine s'il s'agit d'un nom composé ou non
	 * @param ville
	 * @return true si le nom de la ville est composé (s'il comporte au moins un espace), sinon false
	 */
	public boolean estNomComposé(String ville) {
		//à implémenter
	}

	
	/**
	 * retourne les différents éléments d'un nom de ville composé
	 * @param ville
	 * @return List<String> contenant les élements du nom de la ville spécifiée
	 */
	public List<String> getComposantsNom(String ville) {
		//à implémenter
		//regarder du côté de la doc de la classe String et Arrays.asList()
	}

	/**
	 * Méthode 'métier' principale
	 * @param codePostal
	 * @throws Exception
	 */
	public void doTheJob(String codePostal) throws Exception {
		String ville = getVille(codePostal);
		System.out.println("la première ville dont le code postal est " + codePostal + " est " + ville);
		if (estNomComposé(ville)) {
			System.out.println("le nom de cette ville est composé");
			System.out.println("des éléments: " + getComposantsNom(ville));
		}else{
			System.out.println("le nom de cette ville n'est pas un nom composé");
		}
	}

	public static void main(String[] args) {
		try {
			Eval e = new Eval();
			String codePostal = "35136"; //code postal d'une ville au nom composé
			e.doTheJob(codePostal);
			codePostal = "35000"; //code postal évident :)
			e.doTheJob(codePostal);
		} catch (Exception e) {
			System.out.println("oops!");
			e.printStackTrace();
		}
	}

}
