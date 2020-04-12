package ads.pipoca.model.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FilmeDAO {
	public ArrayList<Filme> listarFilme() throws IOException{
		ArrayList<Filme> filme = new ArrayList<>();
		String sql = "SELECT id, nome FROM filme ORDER BY nome";
		
		try(Connection conn = ConnetionFactory.getConnetion();
				PrepareStatement pst = conn.prepareStatement(sql);
				ResultSet rs = pst.ExecuteQuery();){
			
			while(rs.next()) {
				Filme filme = new Filme();
				Filme.setInt(rs.getInt("id"));
				Filme.setString(rs.getString("titulo"));
				Filme.setString(rs.getString("descricao"));
				Filme.setDouble(rs.getDouble("populariedade"));
				Filme.setDate(rs.getDate("dataLancamento"));
				Filme.setString(rs.getString("posterPath"));
				Filme.setString(rs.getString("diretor"));
				Filme.setGenero(rs.getGenero("genero"));
				Filme.add(filme);
			} 
			
			catch (SQLExcaption e){
				e.printStackTrace();
				throw new IOException(e);
			}
			return filme;
		}
		public int Filme inserirFilme() throws IOException {
			int id = -1;
			String sql = "insert into Filme (titulo, descricao, diretor, posterpath, "
					+ "popularidade, data_lancamento, id_genero) values (?,?,?,?,?,?,?)";

			try (Connection conn = ConnectionFactory.getConnection(); PreparedStatement pst = conn.prepareStatement(sql);) {

				pst.setString(1, getTitulo());
				pst.setString(2, getDescricao());
				pst.setString(3, getDiretor());
				pst.setString(4, getPosterPath());
				pst.setDouble(5, getPopularidade());
				pst.setDate(6, new java.sql.Date(getDataLancamento().getTime()));
				pst.setInt(7, getGenero().getId());
				pst.execute();

				// obter o id criado
				String query = "select LAST_INSERT_ID()";
				try (PreparedStatement pst1 = conn.prepareStatement(query); ResultSet rs = pst1.executeQuery();) {

					if (rs.next()) {
						id = rs.getInt(1);
						this.setId(id);
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
			}
			return id;
		}

		public Filme buscarFilme(int id) throws IOException {
			Filme filme = null;
			String sql = "select f.id, titulo, descricao, diretor, posterpath, "
					+ "popularidade, data_lancamento, id_genero, nome "
					+ "from filme f, genero g "
					+ "where f.id_genero = g.id and f.id = ?";

			try (Connection conn = ConnectionFactory.getConnection();
					PreparedStatement pst = conn.prepareStatement(sql);){
				
				pst.setInt(1, id);
				try (ResultSet rs = pst.executeQuery();) {

					while (rs.next()) {
						filme = new Filme();
						filme.setId(id);
						filme.setTitulo(rs.getString("titulo"));
						filme.setDescricao(rs.getString("descricao"));
						filme.setDiretor(rs.getString("diretor"));
						filme.setPosterPath(rs.getString("posterpath"));
						filme.setPopularidade(rs.getDouble("popularidade"));
						filme.setDataLancamento(rs.getDate("data_lancamento"));
						Genero genero = new Genero();
						genero.setId(rs.getInt("id_genero"));
						genero.setNome(rs.getString("nome"));
						filme.setGenero(genero);
					}
				} catch (SQLException e) {
					e.printStackTrace();
					throw new IOException(e);
				}
			} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
			}
			return filme;		
	}
		public Filme excluirFilme() throws IOException{
				String titulo = e;
				String sql = "insert into Filme (titulo, descricao, diretor, posterpath, "
						+ "popularidade, data_lancamento, id_genero) values (?,?,?,?,?,?,?)";

				try (Connection conn = ConnectionFactory.getConnection(); PreparedStatement pst = conn.prepareStatement(sql);) {

					pst.setString(1, getTitulo());
					pst.setString(2, getDescricao());
					pst.setString(3, getDiretor());
					pst.setString(4, getPosterPath());
					pst.setDouble(5, getPopularidade());
					pst.setDate(6, new java.sql.Date(getDataLancamento().getTime()));
					pst.setInt(7, getGenero().getId());
					pst.execute();

					// obter o id criado
					String query = "DELETE filme WHERE titulo = filme.titulo";
					try (PreparedStatement pst1 = conn.prepareStatement(query); ResultSet rs = pst1.executeQuery();) {

						if (rs.next()) {
							id = rs.getInt(1);
							this.setId(id);
						}
					}
				} catch (SQLException e) {
					e.printStackTrace();
					throw new IOException(e);
				}
				return titulo;
		}
		
		public ArrayList <Filme> listarTodosFilme() throw IOException{
			ArrayList<Filme> listarTodosFilmes = new ArrayList<>();
			String sql = "SELECT * FROM filme ORDER BY id";
			
			try(Connection conn = ConnetionFactory.getConnetion();
					PrepareStatement pst = conn.prepareStatement(sql);
					ResultSet rs = pst.ExecuteQuery();){
				
				while(rs.next()) {
					Filme filme = new Filme();
					Filme.setInt(rs.getInt("id"));
					Filme.setString(rs.getString("titulo"));
					Filme.setString(rs.getString("descricao"));
					Filme.setDouble(rs.getDouble("populariedade"));
					Filme.setDate(rs.getDate("dataLancamento"));
					Filme.setString(rs.getString("posterPath"));
					Filme.setString(rs.getString("diretor"));
					Filme.setGenero(rs.getGenero("genero"));
					Filme.add(filme);
				} 
				
				catch (SQLExcaption e){
					e.printStackTrace();
					throw new IOException(e);
				}
				return filme;
			}
		}
		public Filme alterarFilme() throws IOException{
			
		}
}
