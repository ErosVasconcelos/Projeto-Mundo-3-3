package cadastrobd.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SequenceManager {

    public static int getValue(String sequenceName) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int value = 0;
        
        if (sequenceName == null || sequenceName.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome da sequência não pode ser nulo ou vazio.");
        }

        try {
            conn = ConectorBD.getConnection();
            String sql = "SELECT NEXT VALUE FOR " + sequenceName + " AS value";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            if (rs.next()) {
                value = rs.getInt("value");
            }
        } finally {
            ConectorBD.close(rs);
            ConectorBD.close(stmt);
            ConectorBD.close(conn);
        }
        
        return value;
    }
}
