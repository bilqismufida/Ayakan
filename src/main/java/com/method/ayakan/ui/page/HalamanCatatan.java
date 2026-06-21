package com.method.ayakan.ui.page;

import com.method.ayakan.exception.DataNotFoundException;
import com.method.ayakan.model.Catatan;
import com.method.ayakan.model.MataKuliah;
import com.method.ayakan.repository.CatatanRepository;
import com.method.ayakan.service.CatatanManager;
import com.method.ayakan.ui.MissionUtil;

public class HalamanCatatan {

    private static CatatanRepository repoCatatan = new CatatanRepository();
    private static CatatanManager catatanManager = new CatatanManager(repoCatatan);

    public static void halamanCatatan(MataKuliah matkulTerpilih) {
        boolean diHalIni = true;

        while (diHalIni) {
            // Desain menu kotak-kotak ala dashboard utama lu
            System.out.println("\n+==============================================+");
            System.out.printf("| %-44s |%n", "MENU CATATAN: " + matkulTerpilih.getNamaMatkul().toUpperCase());
            System.out.println("+==============================================+");
            System.out.println("|  [1]  Tambah Catatan                         |");
            System.out.println("|  [2]  Tampilkan Semua Catatan                |");
            System.out.println("|  [3]  Update Catatan                         |");
            System.out.println("|  [4]  Hapus Catatan                          |");
            System.out.println("+----------------------------------------------+");
            System.out.println("|  [0]  Kembali ke Detail Matkul               |");
            System.out.println("+==============================================+");
            System.out.print("  Pilih menu: ");

            String aksi = MissionUtil.getUserInput();
            if (aksi == null) {
                aksi = "";
            }

            try {
                switch (aksi) {
                    case "1":
                        System.out.println("\n# TAMBAH CATATAN #");
                        System.out.print("Masukkan Judul Catatan: ");
                        String judul = MissionUtil.getUserInput();

                        System.out.print("Masukkan Isi: ");
                        String isi = MissionUtil.getUserInput();

                        catatanManager.tambah(matkulTerpilih, judul, isi);
                        break;

                    case "2":
                        catatanManager.tampilkanSemua(matkulTerpilih);
                        System.out.print("\nSilakan tekan enter untuk melanjutkan..");
                        MissionUtil.getUserInput();
                        break;

                    case "3":
                        if (catatanManager.isCatatanKosong(matkulTerpilih)) {
                            System.out.println("\n[Info] Data masih kosong! Belum ada Catatan yang bisa diubah.");
                            System.out.print("Silakan tekan Enter untuk kembali ke menu...");
                            MissionUtil.getUserInput();
                            break;
                        }

                        System.out.println("\n# UPDATE CATATAN #");
                        catatanManager.tampilkanSemua(matkulTerpilih);

                        System.out.print("Masukkan ID Catatan yang ingin diubah: ");
                        int idUpdCatatan = Integer.parseInt(MissionUtil.getUserInput());
                        
                        try {
                            if (!repoCatatan.check(idUpdCatatan)) {
                                throw new DataNotFoundException("Catatan dengan ID " + idUpdCatatan + " tidak ditemukan");
                            }

                            Catatan cLama = repoCatatan.findById(idUpdCatatan);
                            System.out.println("----------------------------------------");
                            System.out.println("Judul Lama : " + cLama.getJudulCatatan());
                            System.out.println("Isi Lama   : " + cLama.getIsiCatatan());
                            System.out.println("----------------------------------------");

                            System.out.print("Masukkan Judul Baru: ");
                            String judulBaru = MissionUtil.getUserInput();
                            System.out.print("Masukkan Isi Baru: ");
                            String isiBaru = MissionUtil.getUserInput();

                            catatanManager.update(matkulTerpilih, idUpdCatatan, judulBaru, isiBaru);
                        } catch (DataNotFoundException e) {
                            System.out.println("[Error] " + e.getMessage());
                        }
                        break;

                    case "4":
                        if (catatanManager.isCatatanKosong(matkulTerpilih)) {
                            System.out.println("\n[Info] Data masih kosong! Belum ada Catatan yang bisa dihapus.");
                            System.out.print("Silakan tekan Enter untuk kembali ke menu...");
                            MissionUtil.getUserInput();
                            break;
                        }

                        System.out.println("\n# HAPUS CATATAN #");
                        catatanManager.tampilkanSemua(matkulTerpilih);
                        System.out.print("Masukkan ID Catatan yang ingin dihapus: ");
                        int idDel = Integer.parseInt(MissionUtil.getUserInput());

                        catatanManager.hapus(matkulTerpilih, idDel);
                        break;

                    case "0":
                        diHalIni = false;
                        break;

                    default:
                        System.out.println("Pilihan tidak valid!");
                }
            } catch (NumberFormatException e) {
                System.out.println("[Error] Input tidak valid! Harap masukkan format angka untuk ID");
            }
        }
    }
}