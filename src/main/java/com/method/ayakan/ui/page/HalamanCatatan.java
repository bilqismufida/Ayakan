package com.method.ayakan.ui.page;

import com.method.ayakan.exception.DataNotFoundException;
import com.method.ayakan.model.MataKuliah;
import com.method.ayakan.repository.CatatanRepository;
import com.method.ayakan.service.CatatanManager;
import com.method.ayakan.ui.MissionUtil;
import com.method.ayakan.ui.UITerminal;

public class HalamanCatatan {

    private static CatatanRepository repoCatatan = new CatatanRepository();

    private static CatatanManager catatanManager = new CatatanManager(repoCatatan);

    public static void halamanCatatan(MataKuliah matkulTerpilih) {
        boolean diHalIni = true;

        while (diHalIni) {
            UITerminal.h1("CATATAN - " + matkulTerpilih.getNamaMatkul());

            System.out.println("1. Tambah Catatan");
            System.out.println("2. Tampilkan Semua Catatan");
            System.out.println("3. Update Catatan");
            System.out.println("4. Hapus Catatan");
            System.out.println("0. Kembali ke Detail Matkul");
            System.out.print("Pilih menu: ");

            String aksi = MissionUtil.getUserInput();
            if (aksi == null) {
                aksi = "";
            }

            try {
                switch (aksi) {
                    case "1":
                        System.out.print("Masukkan ID Catatan: ");
                        int idBaru = Integer.parseInt(MissionUtil.getUserInput());

                        try {
                            if (repoCatatan.check(idBaru)) {
                                throw new DataNotFoundException("Catatan dengan ID " + idBaru + " sudah ada, silahkan gunakan ID lain");
                            }

                            System.out.print("Masukkan Judul Catatan: ");
                            String judul = MissionUtil.getUserInput();

                            System.out.print("Masukkan Isi: ");
                            String isi = MissionUtil.getUserInput();

                            catatanManager.tambah(matkulTerpilih, idBaru, judul, isi);
                        } catch (DataNotFoundException e) {
                            System.out.println("[Error] " + e.getMessage());
                        }
                        break;

                    case "2":
                        catatanManager.tampilkanSemua(matkulTerpilih);
                        System.out.println("\nSilahkan tekan enter untuk melanjutkan..");
                        MissionUtil.getUserInput();
                        break;

                    case "3":
                        catatanManager.tampilkanSemua(matkulTerpilih);

                        System.out.print("Masukkan ID Catatan yang ingin diubah: ");
                        int idUpdCatatan = Integer.parseInt(MissionUtil.getUserInput());
                        try {
                            if (!repoCatatan.check(idUpdCatatan)) {
                                throw new DataNotFoundException("Catatan dengan ID " + idUpdCatatan + " tidak ditemukan");
                            }

                            System.out.println("Judul Lama: " + repoCatatan.findById(idUpdCatatan));
                            System.out.print("Masukkan Judul Baru: ");
                            String judulBaru = MissionUtil.getUserInput();
                            System.out.print("Masukkan Isi Baru: ");
                            String urlBaru = MissionUtil.getUserInput();

                            catatanManager.update(matkulTerpilih, idUpdCatatan, judulBaru, urlBaru);
                        } catch (DataNotFoundException e) {
                            System.out.println("[Error] " + e.getMessage());
                        }
                        break;

                    case "4":
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
