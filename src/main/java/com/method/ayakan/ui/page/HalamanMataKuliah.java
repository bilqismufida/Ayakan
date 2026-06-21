package com.method.ayakan.ui.page;

//import com.method.ayakan.exception.DataNotFoundException;
import com.method.ayakan.exception.DataNotFoundException;
import com.method.ayakan.model.MataKuliah;
import com.method.ayakan.service.MataKuliahManager;
import com.method.ayakan.ui.ColorTerminal;
import com.method.ayakan.ui.MissionUtil;
import com.method.ayakan.ui.UITerminal;

public class HalamanMataKuliah {
    
    ColorTerminal msg = new ColorTerminal();

    public static void halMatkul(MataKuliahManager mkManager) {
        boolean diHalIni = true;

        while (diHalIni) {
            mkManager.tampilkanSemua();
            System.out.println("+------------------------------------------------------+");
            System.out.println("[ID Matkul] untuk detail Matkul.");
            System.out.println("[T] Tambah Mata Kuliah.");
            System.out.println("[0] Kembali ke Dashboard.");
            System.out.print("Pilih aksi: ");

            String aksi = MissionUtil.getUserInput();
            if (aksi == null) {
                aksi = "";
            }

            if (aksi.equals("0")) {
                diHalIni = false;
            } else if (aksi.equalsIgnoreCase("T")) {
                tambahMatkulBaru(mkManager);
            } else {
                try {
                    int idTerpilih = Integer.parseInt(aksi);
                    MataKuliah matkulTerpilih = mkManager.cariMatkulById(idTerpilih);

                    if (matkulTerpilih != null) {
                        halamanDetailMatkul(mkManager, matkulTerpilih);
                    } else {
                        System.out.println("Mata Kuliah dengan ID " + idTerpilih + " tidak ditemukan.");
                        System.out.println("Tekan enter untuk coba lagi...");
                        MissionUtil.getUserInput();
                    }
                } catch (NumberFormatException e) {
                    System.out.println("[error] Input tidak valid!");
                }
            }
        }
    }

    // MENU DETAIL MATA KULIAH
    private static void halamanDetailMatkul(MataKuliahManager mkManager, MataKuliah matkulTerpilih) {
        boolean diDetail = true;
        while (diDetail) {
            System.out.println("\n========================================");
            System.out.println(" DETAIL MATA KULIAH: " + matkulTerpilih.getNamaMatkul());
            System.out.println("========================================");
            System.out.println("1. Edit Nama Mata Kuliah");
            System.out.println("2. Hapus Mata Kuliah");
            System.out.println("3. Kelola Catatan");
            System.out.println("4. Kelola Link");
            System.out.println("0. Kembali ke Daftar Matkul");
            System.out.print("Pilih aksi: ");

            String aksi = MissionUtil.getUserInput();

            switch (aksi) {
                case "1":
                    System.out.print("Masukkan Nama Baru: ");
                    String namaBaru = MissionUtil.getUserInput();
                    mkManager.update(matkulTerpilih.getId(), namaBaru);
                    break;
                case "2":
                    mkManager.hapus(matkulTerpilih.getId());
                    diDetail = false; //mengeluarkan user dari halaman ini
                    break;
                case "3":
                    HalamanCatatan.halamanCatatan(matkulTerpilih);
                    break;
                case "4":
                    HalamanLink.halamanLink(matkulTerpilih);
                    break;
                case "0":
                    diDetail = false;
                    break;
                default:
                    System.out.println("[error] Input tidak valid!");
            }
        }
    }

    private static void tambahMatkulBaru(MataKuliahManager mkManager) {

        try {
            System.out.println("\n# MENU TAMBAH MATA KULIAH #");
            System.out.print("Masukkan ID Matkul: ");
            int idBaru = Integer.parseInt(MissionUtil.getUserInput());

            try {
                if (mkManager.cariMatkulById(idBaru) != null) {
                    throw new DataNotFoundException("Mata Kuliah dengan ID " + idBaru + " sudah ada, silahkan gunakan ID lain");
                }
                
                if (idBaru <= 0) {
                   throw new IllegalArgumentException("ID Mata Kuliah tidak boleh < 0 atau 0");
                }

                System.out.print("Masukkan Nama Matkul: ");
                String namaMatkul = MissionUtil.getUserInput();

                mkManager.tambah(idBaru, namaMatkul);

            } catch (DataNotFoundException e) {
                System.out.println("[Error] " + e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println("[Error] " + e.getMessage());
            }
        } catch (NumberFormatException e) {
            System.out.println("[Error] Input tidak valid! ID harus berupa angka.");
        } catch (Exception e) {
            System.out.println("[Error] Terjadi kesalahan saat menambah matkul.");
        }
    }
}
