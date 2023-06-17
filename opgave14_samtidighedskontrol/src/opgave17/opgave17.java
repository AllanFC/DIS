package opgave17;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class opgave17 {
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

            System.out.println("Enter the withdrawal account");
            String fromAccount = sc.nextLine();
            System.out.println("Enter the deposit account");
            String toAccount = sc.nextLine();
            System.out.println("Enter amount to transfer");
            String amount = sc.nextLine();
            int fromBalance = 0;
            int toBalance = 0;
            boolean proceed = !(fromAccount.trim().equals(toAccount.trim()));

            if(proceed){
                minConnection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
                minConnection.setAutoCommit(false);
                ResultSet resFromAccount = stmt.executeQuery("select saldo from konto where kontonr = " + fromAccount);
                if(resFromAccount.next()){ // check if withdrawal account exists and if the balance allows the amount transfered
                    fromBalance = resFromAccount.getInt(1);
                    proceed = (fromBalance - Integer.parseInt(amount)) >= 0;
                } else {
                    proceed = false;

                }

                if(proceed){
                    ResultSet resToAccount = stmt.executeQuery("select saldo from konto where kontonr = " + toAccount);
                    if(resToAccount.next()){ // check if the deposit account exists
                        toBalance = resToAccount.getInt(1);
                    } else {
                        proceed = false;
                    }
                }

                if(proceed){
                    sc.nextLine();
                    fromBalance -= Integer.parseInt(amount);
                    toBalance += Integer.parseInt(amount);
                    stmt.execute("update konto set saldo = " + fromBalance + " where kontonr = " + fromAccount);
                    stmt.execute("update konto set saldo = " + toBalance + " where kontonr = " + toAccount);
                    minConnection.commit();
                    minConnection.setAutoCommit(true);
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
