package opgave19;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class opgave19 {
    public static void main(String[] args) {
    // TODO Auto-generated method stub
        try {
            System.out.println("Program startet");
            Connection minConnection;
            String server = "localhost";
            String dbnavn = "disdb"; // <---- Skift DB navn
            String login = "sa";
            String password = "MyPass@word";  // <----- skal måske skiftes
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            minConnection = DriverManager.getConnection("jdbc:sqlserver://" + server + ";databaseName=" + dbnavn +
                    ";user=" + login + ";password=" + password + ";");

            Scanner sc = new Scanner(System.in);

            Statement stmt =
                    minConnection.createStatement();
            ResultSet res=stmt.executeQuery("select * from konto");
            while (res.next()) {
                System.out.println("Konto " + res.getInt(1) +
                        " har saldo " + res.getInt(2) + " og ejer " + res.getString(3) );
            }

            System.out.println("Enter account number");
            String accountNumber = sc.nextLine();
            String amount = "0";
            boolean proceed = true;
//            stmt.execute("ALTER DATABASE disdb set ALLOW_SNAPSHOT_ISOLATION on");
//            stmt.execute("set transaction isolation level snapshot");
            minConnection.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);

            if(proceed){
                minConnection.setAutoCommit(false);
                ResultSet resAccount = stmt.executeQuery("select * from konto where kontonr = " + accountNumber);
                if(resAccount.next()){
                    System.out.println("AccountNumber: " + resAccount.getInt(1) + " Balance: " +
                                resAccount.getInt(2) + " Owner: " + resAccount.getString(3));

                    System.out.println("Enter new balance");
                    amount = sc.nextLine();
                } else {
                    proceed = false;
                }

                if(proceed){
                    stmt.execute("update konto set saldo = " + amount + " where kontonr = " + accountNumber);
                    minConnection.commit();
                }

                if(proceed){
                    ResultSet resAgain = stmt.executeQuery("select * from konto");
                    while (resAgain.next()) {
                        System.out.println("Konto " + resAgain.getInt(1) +
                                " har saldo " + resAgain.getInt(2) + " og ejer " + resAgain.getString(3) );
                    }
                }

                if(!proceed){
                    minConnection.rollback();
                }
            }

            if (stmt != null)
                stmt.close();
            if (minConnection != null)
                minConnection.close();
        }
        catch (Exception e) {
            System.out.print("fejl: "+e.getMessage());
        }
    }
}
