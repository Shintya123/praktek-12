import java.util.List;

public class MainApp {

    public static void main(String[] args) {

        // Membuat objek DAO
        HeroDAO databaseKu = new HeroDAO();

        // ===================================
        // PROSES INSERT
        // ===================================
        System.out.println("--- PROSES INSERT DATA ---");

        Hero pahlawanSatu = new Hero("Gatotkaca", 99);
        Hero pahlawanDua = new Hero("Kadita", 45);

        databaseKu.simpanPahlawan(pahlawanSatu);
        databaseKu.simpanPahlawan(pahlawanDua);

        // ===================================
        // PROSES UPDATE
        // ===================================
        System.out.println("\n--- PROSES UPDATE DATA ---");
        databaseKu.updateLevel(1, 100);

        // ===================================
        // PROSES DELETE
        // ===================================
        System.out.println("\n--- PROSES DELETE DATA ---");
        databaseKu.hapusHero(2);

        // ===================================
        // MENAMPILKAN ISI DATABASE
        // ===================================
        System.out.println("\n--- ISI DATABASE SETELAH UPDATE & DELETE ---");

        List<Hero> semuaData = databaseKu.ambilSemuaHero();

        for (Hero heroDB : semuaData) {
            System.out.println("[*] ID: " + heroDB.getId()
                    + " | Nama: " + heroDB.getNama()
                    + " | Level: " + heroDB.getLevel());
        }
    }
}