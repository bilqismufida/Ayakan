package com.method.ayakan.ui.page;

import com.method.ayakan.exception.DataNotFoundException;
import com.method.ayakan.model.MataKuliah;
import com.method.ayakan.repository.LinkRepository;
import com.method.ayakan.service.LinkManager;
import com.method.ayakan.ui.MissionUtil;
import com.method.ayakan.ui.UITerminal;

public class HalamanLink {

    private static LinkRepository repoLink = new LinkRepository();

    private static LinkManager linkManager = new LinkManager(repoLink);

    public static void halamanLink(MataKuliah matkulTerpilih) {
        boolean diHalIni = true;

        while (diHalIni) {
            UITerminal.h1("LINK - " + matkulTerpilih.getNamaMatkul());

            System.out.println("1. Tambah Link");
            System.out.println("2. Tampilkan Semua Link");
            System.out.println("3. Update Link");
            System.out.println("4. Hapus Link");
            System.out.println("0. Kembali ke Detail Matkul");
            System.out.print("Pilih menu: ");

            String aksi = MissionUtil.getUserInput();
            if (aksi == null) {
                aksi = "";
            }

            try {
                switch (aksi) {
                    case "1":
                        System.out.print("Masukkan ID Link: ");
                        int idBaru = Integer.parseInt(MissionUtil.getUserInput());

                        try {
                            if (repoLink.check(idBaru)) {
                                throw new DataNotFoundException("Link dengan ID " + idBaru + " sudah ada, silahkan gunakan ID lain");
                            }

                            System.out.print("Masukkan Judul Link: ");
                            String judul = MissionUtil.getUserInput();

                            System.out.print("Masukkan URL: ");
                            String url = MissionUtil.getUserInput();

                            linkManager.tambah(matkulTerpilih, idBaru, judul, url);
                        } catch (DataNotFoundException e) {
                            System.out.println("[Error] " + e.getMessage());
                        }
                        break;

                    case "2":
                        linkManager.tampilkanSemua(matkulTerpilih);
                        System.out.println("\nSilahkan tekan enter untuk melanjutkan..");
                        MissionUtil.getUserInput();
                        break;

                    case "3":
                        linkManager.tampilkanSemua(matkulTerpilih);

                        System.out.print("Masukkan ID Link yang ingin diubah: ");
                        int idUpdLink = Integer.parseInt(MissionUtil.getUserInput());
                        try {
                            if (!repoLink.check(idUpdLink)) {
                                throw new DataNotFoundException("Link dengan ID " + idUpdLink + " tidak ditemukan");
                            }

                            System.out.println("Judul Lama: " + repoLink.findById(idUpdLink));
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
                        linkManager.tampilkanSemua(matkulTerpilih);
                        System.out.print("Masukkan ID Link yang ingin dihapus: ");
                        int idDel = Integer.parseInt(MissionUtil.getUserInput());

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
