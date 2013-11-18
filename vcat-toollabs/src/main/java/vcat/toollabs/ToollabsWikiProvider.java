package vcat.toollabs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import vcat.VCatException;

public class ToollabsWikiProvider {

	private final Connection connection;

	public ToollabsWikiProvider(final Connection connection) {
		this.connection = connection;
	}

	public ToollabsWiki fromDbname(final String dbnameParam) throws VCatException {
		try {
			final PreparedStatement statement = connection.prepareStatement("SELECT * FROM wiki WHERE dbname=?");
			statement.setString(1, dbnameParam);
			ResultSet rs = statement.executeQuery();
			if (!rs.first()) {
				rs.close();
				throw new VCatException(String.format(
						Messages.getString("ToollabsWikiProvider.Exception.DbnameNotFound"), dbnameParam));
			}
			final String dbname = rs.getString("dbname");
			final String name = rs.getString("name");
			final String url = rs.getString("url");
			rs.close();
			return new ToollabsWiki(dbname, name, url);
		} catch (SQLException e) {
			throw new VCatException(String.format(Messages.getString("ToollabsWikiProvider.Exception.ReadingMetaInfo"),
					dbnameParam), e);
		}
	}

}
