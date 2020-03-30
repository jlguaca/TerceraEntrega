
package servidorcentral.negocio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Esta clase se conecta a la base de datos con Jdbc
 *
 *
 */
public class ConectorJdbc {

    private Connection cn;
    private ResultSet rs;
    private Statement st;
    private final String URL = "jdbc:hsqldb:file:D:\\disco pc acer\\universidad\\lab. soft. 2\\Primera_Entrega\\servidorCentral\\bd\\parqueadero;hsqldb.lock_file=false";
    //Cambie la URL de su bd local, por ejemplo, si tiene Windows,sería algo como:
    //private final String URL = "jdbc:hsqldb:file:C:\\clientes\\bd\\clientes;hsqldb.lock_file=false";
    
    private final String USER = "vigilante";
    private final String PASSWORD = "123456789";

    public ConectorJdbc() {

    } 

    public void conectarse() throws ClassNotFoundException, SQLException {
        Class.forName("org.hsqldb.jdbcDriver");
        cn = DriverManager.getConnection(URL, USER, PASSWORD);
        if(cn!=null)
        {
            System.out.println("si reconoce");
        }
    }

    /**
     * Ejecuta una consulta de tipo select
     * @param sql
     * @throws SQLException 
     */
    public void crearConsulta(String sql) throws SQLException {
        System.out.println(sql);
        st = cn.createStatement();
       
        rs = st.executeQuery(sql);
    }

    /**
     * Ejecuta una consulta de tipo insert, update o delete
     *
     * @param sql
     * @throws SQLException
     */
    public void actualizar(String sql) throws SQLException {
        st = cn.createStatement();
        st.executeUpdate(sql);
    }
    /**
     * Cierra las variables de rs, st y cn que estén abiertas
     * @throws SQLException 
     */
    public void desconectarse() throws SQLException {
        if ( rs != null){
            rs.close();
        }
        st.close();
        cn.close();
    }
    /**
     * Devuelve todo el conjunto de resultados
     * @return 
     */
    public ResultSet getResultado() {
        return rs;
    }
}
