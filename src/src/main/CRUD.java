package src.main;
import java.sql.*;

public class CRUD {
    public static Connection ConnectBdd() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
       Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mmo", "root","root" );
       return conn;
    }

    public static void InsertJoueur(String pseudo) throws SQLException, ClassNotFoundException {
        Connection conn = ConnectBdd();
        try {
            String sql = "INSERT INTO joueur (pseudo) VALUES (?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, pseudo);
            stmt.executeUpdate();

        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Integer recupIdParPseudo(String pseudo) throws SQLException, ClassNotFoundException {
        Connection conn = ConnectBdd();

            String sql = "SELECT idJoueur from joueur where pseudo = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, pseudo);
            ResultSet result = stmt.executeQuery();
            Integer resultId = null;
            while (result.next()) {
                resultId = result.getInt(1);
            }
            return resultId;

    }

    public static void InsertPersonnage(Personnage p, int idJ)  {

        try {
            Connection conn = ConnectBdd();
            String sql = "INSERT INTO personnage (nomPerso, niveau, sante, santeMax, niveauExp, prochainNiveau, idJoueur) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, p.getClass().getSimpleName());
            stmt.setInt(2, p.getNiveau());
            stmt.setInt(3, p.getSante());
            stmt.setInt(4, p.getSanteMax());
            stmt.setInt(5, p.getNiveauExp());
            stmt.setInt(6, p.getProchainNiveau());
            stmt.setInt(7 , idJ);
            stmt.executeUpdate();
        } catch(Exception e){
            e.getStackTrace();
        }
    }

    public static ResultSet getperso(int id) throws SQLException, ClassNotFoundException {

        Connection conn = ConnectBdd();
        String sql = "SELECT * from personnage join joueur on personnage.idJoueur = joueur.idJoueur where personnage.idJoueur = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, id);
        ResultSet resultat =  stmt.executeQuery();
        return resultat;
    }
}
