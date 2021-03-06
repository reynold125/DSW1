package br.ufscar.dc.dsw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;



import br.ufscar.dc.dsw.domain.Hotel;


//alterar todos os argumentos para a estrutura do Hotel
public class HotelDAO extends GenericDAO {

    public void insert(Hotel hotel) {    
        String sql = "INSERT INTO Hotel(cnpj, nome, cidade, email, senha) VALUES (?, ?, ?, ?, ?)";
        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);;    
            statement = conn.prepareStatement(sql);
            statement.setString(1, hotel.getCNPJ());
            statement.setString(2, hotel.getNome());
            statement.setString(3, hotel.getCidade());
            statement.setString(4, hotel.getEmail());
            statement.setString(5, hotel.getSenha());
            statement.executeUpdate();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        
    }
    
    public List<Hotel> getAll() {
        
        List<Hotel> listaHotels = new ArrayList<>();
        String sql = "SELECT * FROM Hotel";
        try {
            Connection conn = this.getConnection();
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                String CNPJ = resultSet.getString("cnpj");
                String nome = resultSet.getString("nome");
                String cidade = resultSet.getString("cidade");
                String email = resultSet.getString("email");
                String senha = resultSet.getString("senha");
                Hotel hotel = new Hotel(email, senha, CNPJ, nome, cidade);
                listaHotels.add(hotel);
            }
            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            
            throw new RuntimeException(e);
        }
        return listaHotels;
    }
    
    public void delete(Hotel hotel) {
        String sql = "DELETE FROM Hotel where cnpj = ?";
        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, hotel.getCNPJ());
            statement.executeUpdate();
            statement.close();
            conn.close();
        } catch (SQLException e) {
        }
    }
    
    public void update(Hotel hotel) {
        String sql = "UPDATE Hotel SET nome = ?, cidade = ?, email = ?, senha = ? WHERE cnpj = ?";
    
        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, hotel.getNome());
            statement.setString(2, hotel.getCidade());
            statement.setString(3, hotel.getEmail());
            statement.setString(4, hotel.getSenha());
            statement.setString(5, hotel.getCNPJ());
            statement.executeUpdate();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public Hotel getbyCNPJ(String cnpj) {
        Hotel hotel = null;
        String sql = "SELECT * from Hotel WHERE cnpj = ?";
        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, cnpj);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String nome = resultSet.getString("nome");
                String cidade = resultSet.getString("cidade");
                String email = resultSet.getString("email");
                String senha = resultSet.getString("senha");
                String CNPJ = resultSet.getString("cnpj");
                hotel = new Hotel(email, senha, CNPJ, nome, cidade);
            }
            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return hotel;
    }

    public Hotel getByEmail(String email) {
        Hotel hotel = null;
        String sql = "SELECT * from Hotel WHERE email = ?";
        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()) {
                String nome = resultSet.getString("nome");
                String cidade = resultSet.getString("cidade");
                //String email = resultSet.getString("email");
                String senha = resultSet.getString("senha");
                String CNPJ = resultSet.getString("cnpj");
                hotel = new Hotel(email, senha, CNPJ, nome, cidade);
            }
            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return hotel;
    }
    
    public List<Hotel> getbyCidade(String cidade) {
        List<Hotel> listaHotel = new ArrayList<>();
        String sql = "SELECT * from Hotel WHERE cidade = ?";
        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, cidade);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
            	String nome = resultSet.getString("nome");
                //String cidadeT = resultSet.getString("cidade");
                String email = resultSet.getString("email");
                String senha = resultSet.getString("senha");
                String CNPJ = resultSet.getString("cnpj");
                Hotel hotel = new Hotel(email, senha, CNPJ, nome, cidade);
                listaHotel.add(hotel);
            }
            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaHotel;
    }

    public List<String> getCidades(){
        List<String> cidades = new ArrayList<>();
        String sql = "SELECT DISTINCT cidade FROM Hotel";
        try {
            Connection conn = this.getConnection();
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                String nomeCidade = resultSet.getString("cidade");
                cidades.add(nomeCidade);
            }
            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cidades;
    }
    
}

