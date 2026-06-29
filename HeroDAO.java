import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HeroDAO {

    // Method untuk MENYIMPAN (Insert)
    public void simpanPahlawan(Hero h) {

        String sql = "INSERT INTO hero (nama, level) VALUES (?, ?)";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setString(1, h.getNama());
            pst.setInt(2, h.getLevel());

            pst.executeUpdate();

            System.out.println("SUKSES: Hero " + h.getNama() + " berhasil masuk DB!");

        } catch (SQLException e) {
            System.out.println("GAGAL: " + e.getMessage());
        }
    }

    // Method untuk MEMBACA (Select)
    public List<Hero> ambilSemuaHero() {

        List<Hero> daftarHero = new ArrayList<>();

        String sql = "SELECT * FROM hero";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {

                Hero h = new Hero(
                        rs.getString("nama"),
                        rs.getInt("level")
                );

                h.setId(rs.getInt("id"));

                daftarHero.add(h);
            }

        } catch (SQLException e) {
            System.out.println("Error pas narik data: " + e.getMessage());
        }

        return daftarHero;
    }

    // Method untuk UPDATE
    public void updateLevel(int id_target, int level_baru) {

        String sql = "UPDATE hero SET level = ? WHERE id = ?";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setInt(1, level_baru);
            pst.setInt(2, id_target);

            int hasil = pst.executeUpdate();

            if (hasil > 0) {
                System.out.println("SUKSES: Level Hero berhasil diupdate!");
            } else {
                System.out.println("Hero dengan ID " + id_target + " tidak ditemukan.");
            }

        } catch (SQLException e) {
            System.out.println("Error Update: " + e.getMessage());
        }
    }

    // Method untuk DELETE
    public void hapusHero(int id_target) {

        String sql = "DELETE FROM hero WHERE id = ?";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {

            pst.setInt(1, id_target);

            int hasil = pst.executeUpdate();

            if (hasil > 0) {
                System.out.println("SUKSES: Hero berhasil dihapus!");
            } else {
                System.out.println("Hero dengan ID " + id_target + " tidak ditemukan.");
            }

        } catch (SQLException e) {
            System.out.println("Error Delete: " + e.getMessage());
        }
    }
}