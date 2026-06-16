package com.method.ayakan.ui.page;

import com.method.ayakan.exception.DataNotFoundException;
import com.method.ayakan.repository.LinkRepository;
import com.method.ayakan.repository.MataKuliahRepository;
import com.method.ayakan.service.LinkManager;
import com.method.ayakan.service.MataKuliahManager;
import com.method.ayakan.ui.MissionUtil;
import com.method.ayakan.ui.UITerminal;

public class HalamanLink {

    private static LinkManager linkManager;
    private static MataKuliahManager mkManager;

    private static UITerminal cover;

    public static void halamanLink() {

        MataKuliahRepository repoMk = new MataKuliahRepository();
        mkManager = new MataKuliahManager(repoMk);
        LinkRepository repo = new LinkRepository();
        linkManager = new LinkManager(repo);

        boolean diHalIni = true;

        while (diHalIni) {
            cover.h1("Link");
            System.out.println("1. Tambah Link");
            System.out.println("2. Tampilkan Semua Link");
            System.out.println("3. Update Link");
            System.out.println("4. Hapus Link");
            System.out.println("0. Kembali ke Dashboard");
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
                            if (repo.check(idBaru)) {
                                throw new DataNotFoundException("Link dengan ID " + idBaru + " sudah ada, silahkan gunakan ID lain");
                            }

                            System.out.println("Pilih Mata Kuliah:");
                            mkManager.tampilkanSemua();
                            System.out.print("Pilih Matkul: ");
                            int idMatkul = Integer.parseInt(MissionUtil.getUserInput());
                            if (!repo.check(idBaru)) {
                                throw new DataNotFoundException("Matkul dengan ID " + idBaru + " tidak ada, silahkan gunakan matkul lain");
                            }

                            System.out.print("Masukkan Judul Link: ");
                            String judul = MissionUtil.getUserInput();

                            System.out.print("Masukkan URL: ");
                            String url = MissionUtil.getUserInput();

                            linkManager.tambah(repoMk.findById(idMatkul), judul, url);
                        } catch (DataNotFoundException e) {
                            System.out.println("[Error] " + e.getMessage());
                        }
                        break;
                    case "2":
                        linkManager.tampilkanSemua();
                        System.out.println("\nSilahkan tekan enter untuk melanjutkan..");
                        MissionUtil.getUserInput();
                        break;
                    case "3":
                        if (repo.isEmpty()) {
                            System.out.println("Data kosong, silahkan tambah data terlebih dahulu");
                            break;
                        }
                        System.out.print("Masukkan ID Link yang ingin diubah: ");
                        int idUpdLink = Integer.parseInt(MissionUtil.getUserInput());
                        try {
                            if (!repo.check(idUpdLink)) {
                                throw new DataNotFoundException("Link dengan ID " + idUpdLink + " tidak ditemukan");
                            }

                            System.out.println("Nama Link Sebelumnya [" + repo.findById(idUpdLink) + "]");
                            System.out.print("Masukkan Nama Link Baru: ");
                            String mkBaru = MissionUtil.getUserInput();
                            linkManager.update(idUpdLink, mkBaru);
                        } catch (DataNotFoundException e) {
                            System.out.println("[Error] " + e.getMessage());
                        }
                        break;
                    case "4":
                        System.out.print("Masukkan ID Link yang ingin dihapus: ");
                        int idDelLink = Integer.parseInt(MissionUtil.getUserInput());
                        linkManager.hapus(idDelLink);
                    default:
                        System.out.println("Pilihan tidak valid!");
                }
            } catch (NumberFormatException e) {
                System.out.println("[Error] Input tidak valid! Harap masukkan format angka untuk ID");
            }
        }
    }
}
