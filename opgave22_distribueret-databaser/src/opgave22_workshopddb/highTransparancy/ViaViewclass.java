package opgave22_workshopddb.highTransparancy;
import java.sql.*;
import java.util.*;
import opgave22_workshopddb.lowTransparancy.*;
public class ViaViewclass {
    public static void main(String[] args) {
        try {
            ArrayList<Person> liste = new ArrayList<Person>();
//	 læser viewet person via native SQL-Server driver
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection minConnection;
            String server = "localhost";
            String dbnavn = "ddba"; // <---- Skift DB navn
            String login = "sa";
            String password = "MyPass@word";  // <----- skal måske skiftes
            minConnection = DriverManager.getConnection("jdbc:sqlserver://" + server + ";databaseName=" + dbnavn +
                    ";user=" + login + ";password=" + password + ";");

            String sql= "select * from person";
            Statement stmt = minConnection.createStatement();
            ResultSet res=stmt.executeQuery(sql);
            while (res.next()) {
                Person p = new Person();
                p.setCpr(res.getString("cpr"));
                p.setNavn(res.getString("navn"));
                p.setBynavn(res.getString("bynavn"));
                p.setLoen(res.getInt("loen"));
                p.setSkatteprocent(res.getInt("skatteprocent"));
                liste.add(p);
            };
//	 udskriver indholdet af de to tabeller
            for (int i=0;i<liste.size();i++) {
                Person s =liste.get(i);
                System.out.print(s.getCpr() + "\t");
                System.out.print(s.getNavn() + "\t");
                System.out.print(s.getBynavn() + "\t");
                System.out.print(s.getLoen() + "\t");
                System.out.print(s.getSkatteprocent());
                System.out.println();
            }
        }
        catch (Exception e) {
            System.out.println("fejl:  "+e.getMessage());
        }
    }
}
