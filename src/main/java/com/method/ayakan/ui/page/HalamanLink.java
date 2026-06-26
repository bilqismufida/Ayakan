package com.method.ayakan.ui.page;

import com.method.ayakan.exception.DataNotFoundException;
import com.method.ayakan.model.Link;
import com.method.ayakan.model.MataKuliah;
import com.method.ayakan.repository.LinkRepository;
import com.method.ayakan.service.LinkManager;
import com.method.ayakan.ui.MissionUtil;

public class HalamanLink {

    public static void halamanLink(MataKuliah matkulTerpilih, LinkManager linkManager) {
        boolean diHalIni = true;

        while (diHalIni) {
            System.out.println("\n+==============================================+");
            System.out.printf("| %-44s |%n", "MENU LINK: " + matkulTerpilih.getNamaMatkul().toUpperCase());
            System.out.println("+==============================================+");
            System.out.println("|  [1]  Tambah Link                            |");
            System.out.println("|  [2]  Tampilkan Semua Link                   |");
            System.out.println("|  [3]  Update Link                            |");
            System.out.println("|  [4]  Hapus Link                             |");
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
                        System.out.println("\n# TAMBAH LINK #");
                        System.out.print("Masukkan Judul Link: ");
                        String judul = MissionUtil.getUserInput();

                        System.out.print("Masukkan URL: ");
                        String url = MissionUtil.getUserInput();

                        linkManager.tambah(matkulTerpilih, judul, url);
                        break;

                    case "2":
                        linkManager.tampilkanSemua(matkulTerpilih);
                        System.out.print("\nSilahkan tekan enter untuk melanjutkan..");
                        MissionUtil.getUserInput();
                        break;

                    case "3":
                        if (linkManager.isLinkKosong(matkulTerpilih)) {
                            System.out.println("\n[Info] Data masih kosong! Belum ada Link yang bisa diubah.");
                            System.out.print("Silakan tekan Enter untuk kembali ke menu...");
                            MissionUtil.getUserInput();
                            break;
                        }

                        System.out.println("\n# UPDATE LINK #");
                        linkManager.tampilkanSemua(matkulTerpilih);

                        System.out.print("Masukkan ID Link yang ingin diubah: ");
                        int idUpdLink = Integer.parseInt(MissionUtil.getUserInput());

                        try {
                            if (!matkulTerpilih
                                    .getDaftarLink()
                                    .containsKey(idUpdLink)) {

                                throw new DataNotFoundException(
                                        "Link dengan ID "
                                        + idUpdLink
                                        + " tidak ditemukan"
                                );
                            }

                            Link linkLama
                                    = matkulTerpilih
                                            .getDaftarLink()
                                            .get(idUpdLink);

                            System.out.println("----------------------------------------");
                            System.out.println("Judul Lama : "
                                    + linkLama.getJudulLink());
                            System.out.println("URL Lama   : "
                                    + linkLama.getUrl());
                            System.out.println("----------------------------------------");

                            System.out.print("Masukkan Judul Baru: ");
                            String judulBaru = MissionUtil.getUserInput();
                            System.out.print("Masukkan URL Baru: ");
                            String urlBaru = MissionUtil.getUserInput();

                            linkManager.update(matkulTerpilih, idUpdLink, judulBaru, urlBaru);
                        } catch (DataNotFoundException e) {
                            System.out.println("[Error] " + e.getMessage());
                        }
                        break;

                    case "4":
                        if (linkManager.isLinkKosong(matkulTerpilih)) {
                            System.out.println("\n[Info] Data masih kosong! Belum ada Link yang bisa dihapus.");
                            System.out.print("Silakan tekan Enter untuk kembali ke menu...");
                            MissionUtil.getUserInput();
                            break;
                        }

                        System.out.println("\n# HAPUS LINK #");
                        linkManager.tampilkanSemua(matkulTerpilih);
                        System.out.print("Masukkan ID Link yang ingin dihapus: ");
                        int idDel = Integer.parseInt(MissionUtil.getUserInput());

                        System.out.print("Yakin ingin menghapus tugas ini? (Y/N): ");
                        String konfirmasi = MissionUtil.getUserInput();

                        if (!konfirmasi.equalsIgnoreCase("Y")) {
                            System.out.println("Penghapusan dibatalkan.");
                            return;
                        }

                        linkManager.hapus(matkulTerpilih, idDel);
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
