package com.method.ayakan.ui.page;

import com.method.ayakan.exception.DataNotFoundException;
import com.method.ayakan.model.MataKuliah;
import com.method.ayakan.service.MataKuliahManager;
import com.method.ayakan.ui.MissionUtil;

public class HalamanMataKuliah {

    public static void tampilkanMenu(MataKuliahManager mkManager) {
        int idDetail = 0;
        MataKuliah matkulTerpilih;
        

        boolean diHalIni = true;

        while (diHalIni) {
            System.out.println("\n+==============================================+");
            System.out.println("|              MENU MATA KULIAH                |");
            System.out.println("+==============================================+");
            System.out.println("|  [1]  Tambah Mata Kuliah                     |");
            System.out.println("|  [2]  Tampilkan Semua                        |");
            System.out.println("|  [3]  Update Nama Mata Kuliah                |");
            System.out.println("|  [4]  Hapus Mata Kuliah                      |");
            System.out.println("+----------------------------------------------+");
            System.out.println("|  [5]  Halaman Catatan                        |");
            System.out.println("|  [6]  Halaman Link                           |");
            System.out.println("+----------------------------------------------+");
            System.out.println("|  [0]  Kembali ke Dashboard                   |");
            System.out.println("+==============================================+");
            System.out.print("  Pilih menu: ");

            String aksi = MissionUtil.getUserInput();
            if (aksi == null) {
                aksi = "";
            }

            try {
                switch (aksi) {
                    case "1":
                        System.out.println("\n# TAMBAH MATA KULIAH #");
                        System.out.print("Masukkan Nama Matkul: ");
                        String namaMatkul = MissionUtil.getUserInput();
                        mkManager.tambah(namaMatkul);
                        break;

                    case "2":
                        mkManager.tampilkanSemua();
                        System.out.print("Masukkan ID Matkul untuk buka detail (atau 0 untuk batal): ");
                        idDetail = Integer.parseInt(MissionUtil.getUserInput());

                        if (idDetail == 0) {
                            break;
                        }

//                        MataKuliah matkulTerpilih = mkManager.cariMatkulById(idDetail);
//                        if (matkulTerpilih != null) {
//                            halamanDetailMatkul(matkulTerpilih);
//                        } else {
//                            System.out.println("[Error] Mata Kuliah dengan ID " + idDetail + " tidak ditemukan!");
//                        }
//                        break;

                    case "3":
                        if (mkManager.isEmpty()) {
                            System.out.println("\n[Info] Data masih kosong! Belum ada Mata Kuliah yang bisa diubah.");
                            System.out.print("Silakan tekan Enter untuk kembali ke menu...");
                            MissionUtil.getUserInput();
                            break;
                        }

                        System.out.println("\n# UPDATE MATA KULIAH #");
                        mkManager.tampilkanSemua();
                        System.out.print("Masukkan ID Mata Kuliah yang ingin diubah: ");
                        int idUpd = Integer.parseInt(MissionUtil.getUserInput());

                        MataKuliah matkulLama = mkManager.cariMatkulById(idUpd);
                        if (matkulLama == null) {
                            throw new DataNotFoundException("Mata Kuliah dengan ID " + idUpd + " tidak ditemukan");
                        }

                        System.out.println("----------------------------------------");
                        System.out.println("Nama Lama: " + matkulLama.getNamaMatkul());
                        System.out.println("----------------------------------------");
                        System.out.print("Masukkan Nama Baru: ");
                        String namaBaru = MissionUtil.getUserInput();

                        mkManager.update(idUpd, namaBaru);
                        break;

                    case "4":
                        if (mkManager.isEmpty()) {
                            System.out.println("\n[Info] Data masih kosong! Belum ada Mata Kuliah yang bisa dihapus.");
                            System.out.print("Silakan tekan Enter untuk kembali ke menu...");
                            MissionUtil.getUserInput();
                            break;
                        }

                        System.out.println("\n# HAPUS MATA KULIAH #");
                        mkManager.tampilkanSemua();
                        System.out.print("Masukkan ID Mata Kuliah yang ingin dihapus: ");
                        int idDel = Integer.parseInt(MissionUtil.getUserInput());

                        System.out.print("Yakin ingin menghapus tugas ini? (Y/N): ");
                        String konfirmasi = MissionUtil.getUserInput();

                        if (!konfirmasi.equalsIgnoreCase("Y")) {
                            System.out.println("Penghapusan dibatalkan.");
                            return;
                        }

                        mkManager.hapus(idDel);
                        break;

//                        PINDAH HALAMAN KE CTTN
                    case "5":
                        mkManager.tampilkanSemuaDanCatatan();
                        System.out.print("Masukkan ID Matkul untuk lihat Catatan (atau 0 untuk batal): ");
                        idDetail = Integer.parseInt(MissionUtil.getUserInput());

                        if (idDetail == 0) {
                            break;
                        }

                        matkulTerpilih = mkManager.cariMatkulById(idDetail);
                        if (matkulTerpilih != null) {
                            HalamanCatatan.halamanCatatan(matkulTerpilih);
                        } else {
                            System.out.println("[Error] Mata Kuliah dengan ID " + idDetail + " tidak ditemukan!");
                        }
                        break;
//                        PINDAH HALAMAN KE LINK
                    case "6":
                        mkManager.tampilkanSemuaDanLink();
                        System.out.print("Masukkan ID Matkul untuk lihat Catatan (atau 0 untuk batal): ");
                        idDetail = Integer.parseInt(MissionUtil.getUserInput());

                        if (idDetail == 0) {
                            break;
                        }

                        matkulTerpilih = mkManager.cariMatkulById(idDetail);
                        if (matkulTerpilih != null) {
                            HalamanLink.halamanLink(matkulTerpilih);
                        } else {
                            System.out.println("[Error] Mata Kuliah dengan ID " + idDetail + " tidak ditemukan!");
                        }
                        break;

                    case "0":
                        diHalIni = false;
                        break;

                    default:
                        System.out.println("Pilihan tidak valid!");
                }
            } catch (NumberFormatException e) {
                System.out.println("[Error] Input tidak valid! Harap masukkan format angka untuk ID.");
            } catch (DataNotFoundException e) {
                System.out.println("[Error] " + e.getMessage());
            }
        }
    }

}
