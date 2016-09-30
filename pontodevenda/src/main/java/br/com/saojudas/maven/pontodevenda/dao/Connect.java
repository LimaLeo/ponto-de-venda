package br.com.saojudas.maven.pontodevenda.dao;
import java.sql.Connection;

public interface Connect{

    public Connection getConnection();    
    public void closeConnection();
}