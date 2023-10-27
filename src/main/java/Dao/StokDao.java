package Dao;

import com.smk.cashier.model.Stok;

import java.sql.*;
import java.util.Collection;
import java.util.Objects;
import java.util.Optional;

public class StokDao implements Dao<Stok, Integer> {
    private final Optional<Connection> connection;

    public StokDao() {
        connection = JdbcConnection.getConnection();
    }

    @Override
    public Optional<Stok> get(int id) {
        return connection.flatMap(conn -> {
            Optional<Stok> stok = Optional.empty();
            String sql = "SELECT * from stok where id = ?";
            try {
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    int Id = rs.getInt("id");
                    String kodeBarang = rs.getString("kode_barang");
                    int stokBarang = rs.getInt("stok_barang");
                    Stok barangResult = new Stok();
                    barangResult.setId(Id);
                    barangResult.setKodebarang(kodeBarang);
                    barangResult.setStokbarang(stokBarang);

                    stok = Optional.of(barangResult);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return stok;
        });

    }

    @Override
    public Collection<Stok> getAll() {
        return null;
    }

    @Override
    public Optional<Integer> save(Stok stok) {
        Stok nonNullStok = Objects.requireNonNull(stok);
        String query =
                "INSERT INTO stok(id, kode_barang, stok_barang, date_created, last_modified, created_by, updated_by)" +
                        "VALUES(?,?,?,?,?,?,?)";
        return connection.flatMap(conn -> {
            Optional<Integer> generatedId = Optional.empty();
            try {
                PreparedStatement ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1,stok.getId());
                ps.setString(2,stok.getKodebarang());
                ps.setInt(3,stok.getStokbarang());
                ps.setString(4,stok.getCreatedBy());
                ps.setString(5,stok.getUpdatedBy());
                ps.setDate(6,new Date(stok.getDateCreated().getTime()));
                ps.setDate(7,new Date(stok.getLastModified().getTime()));
                int numberOfInsertedRows = ps.executeUpdate();
                if(numberOfInsertedRows > 0) {
                    ResultSet rs = ps.getGeneratedKeys();
                    if(rs.next()){
                        generatedId = Optional.of(rs.getInt(1));
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            return generatedId;
        });
    }

    @Override
    public void update(Stok stok) {

    }

    @Override
    public void delete(Stok stok) {

    }
}