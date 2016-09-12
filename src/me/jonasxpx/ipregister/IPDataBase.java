package me.jonasxpx.ipregister;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



public class IPDataBase {

	private Connection con;
	
	public IPDataBase(String ip, String port, String db, String user, String password) {
		try {
			this.con = DriverManager.getConnection("jdbc:mysql://" + ip + ":" + port + "/" + db, user, password);
			this.con.prepareStatement("CREATE TABLE IF NOT EXISTS ipregister(username VARCHAR(32), address VARCHAR(32), PRIMARY KEY(username))").execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 
	 * @param player
	 * @return retorna String caso o IP do jogador exista, e nulo caso não exista.
	 */
	public String getIP(String player){
		try{
			PreparedStatement ps = this.con.prepareStatement("SELECT ip FROM ipregister WHERE username = ?");
			ps.setString(1, player);
			ResultSet r = ps.executeQuery();
			if(r.next() != false){
				return r.getString(1);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * Altera o IP do jogador, ou remove, com null.
	 *
	 * {@link www.endcraft.com.br}
	 * @param player
	 * @param address IP do jogador, null para remover.
	 */
	public void toggleIP(String player, String address){
		try{
			PreparedStatement ps;
			if(address != null){
				if(getIP(player) == null){
					ps = this.con.prepareStatement("INSERT INTO ipregister VALUES(?, ?)");
					ps.setString(1, player);
					ps.setString(2, address);
					ps.execute();
				} else {
					ps = this.con.prepareStatement("UPDATE ipregister SET address = ? WHERE username = ?");
					ps.setString(1, address);
					ps.setString(2, player);
					ps.execute();
				}
			} else {
				ps = this.con.prepareStatement("DELETE FROM ipregister WHERE username = ?");
				ps.setString(1, player);
				ps.execute();
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
}
