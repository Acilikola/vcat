package vcat.toolforge.base;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import vcat.VCatException;

public class ToolforgeWikiProvider {

	private final ComboPooledDataSource cpds;

	public ToolforgeWikiProvider(final ComboPooledDataSource cpds) {
		this.cpds = cpds;
	}

	public ToolforgeWiki fromDbname(final String dbnameParam) throws VCatException {
		try (Connection connection = this.cpds.getConnection()) {
			final PreparedStatement statement = connection.prepareStatement("SELECT * FROM wiki WHERE dbname=?");
			statement.setString(1, dbnameParam);
			try (ResultSet rs = statement.executeQuery()) {
				if (!rs.first()) {
					rs.close();
					throw new VCatException(String.format(
							Messages.getString("ToolforgeWikiProvider.Exception.DbnameNotFound"), dbnameParam));
				}
				final String dbname = rs.getString("dbname");
				final String name = rs.getString("name");
				final String url = rs.getString("url");
				return new ToolforgeWiki(dbname, name, url);
			}
		} catch (SQLException e) {
			throw new VCatException(String.format(Messages.getString("ToolforgeWikiProvider.Exception.ReadingMetaInfo"),
					dbnameParam), e);
		}
	}

}
