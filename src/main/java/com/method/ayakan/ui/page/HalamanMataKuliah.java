package com.method.ayakan.ui.page;

import com.method.ayakan.exception.DataNotFoundException;
import com.method.ayakan.repository.MataKuliahRepository;
import com.method.ayakan.service.MataKuliahManager;
import com.method.ayakan.ui.MissionUtil;
import com.method.ayakan.ui.UITerminal;

public class HalamanMataKuliah {

    private static MataKuliahManager mkManager ;
    private static UITerminal cover;
    public static void halamanMataKuliah() {
        MataKuliahRepository repo = new MataKuliahRepository();
        mkManager = new MataKuliahManager(repo);

        boolean diHalIni = true;

        while (diHalIni) {
            cover.h1("Mata Kuliah");
            System.out.println("1. Tambah Mata Kuliah");
            System.out.println("2. Tampilkan Semua Mata Kuliah");
            System.out.println("3. Update Mata Kuliah");
            System.out.println("4. Hapus Mata Kuliah");
            System.out.println("0. Kembali ke Dashboard");
            System.out.print("Pilih menu: ");

            String aksi = MissionUtil.getUserInput();
            if (aksi == null) {
                aksi = "";
            }

            try {
                switch (aksi) {
                    case "1":
                        System.out.print("Masukkan ID Matkul: ");
                        int idBaru = Integer.parseInt(MissionUtil.getUserInput());

                        try {
                            if (repo.check(idBaru)) {
                                throw new DataNotFoundException("Mata Kuliah dengan ID " + idBaru + " sudah ada, silahkan gunakan ID lain");
                            }

                            System.out.print("Masukkan Nama Matkul: ");
                            String namaMatkul = MissionUtil.getUserInput();

                            mkManager.tambah(idBaru, namaMatkul);
                        } catch (DataNotFoundException e) {
                            System.out.println("[Error] " + e.getMessage());
                        }
                        break;
                    case "2":
                        mkManager.tampilkanSemua();
                        System.out.println("\nSilahkan tekan enter untuk melanjutkan..");
                        MissionUtil.getUserInput();
                        break;
                    case "3":
                        if (repo.isEmpty()) {
                            System.out.println("Data kosong, silahkan tambah data terlebih dahulu");
                            break;
                        }
                        System.out.print("Masukkan ID Mata Kuliah yang ingin diubah: ");
                        int idUpdMatkul = Integer.parseInt(MissionUtil.getUserInput());
                        try {
                            if (!repo.check(idUpdMatkul)) {
                                throw new DataNotFoundException("Mata Kuliah dengan ID " + idUpdMatkul + " tidak ditemukan");
                            }

                            System.out.println("Nama Mata Kuliah Sebelumnya [" + repo.findById(idUpdMatkul) + "]");
                            System.out.print("Masukkan Nama Mata Kuliah Baru: ");
                            String mkBaru = MissionUtil.getUserInput();
                            mkManager.update(idUpdMatkul, mkBaru);
                        } catch (DataNotFoundException e) {
                            System.out.println("[Error] " + e.getMessage());
                        }
                        break;
                    case "4":
                        System.out.print("Masukkan ID Mata Kuliah yang ingin dihapus: ");
                        int idDelMatkul = Integer.parseInt(MissionUtil.getUserInput());
                        mkManager.hapus(idDelMatkul);
                    default:
                        System.out.println("Pilihan tidak valid!");
                }
            } catch (NumberFormatException e) {
                System.out.println("[Error] Input tidak valid! Harap masukkan format angka untuk ID");
            }
        }
    }
}
