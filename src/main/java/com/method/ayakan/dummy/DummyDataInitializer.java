package com.method.ayakan.dummy;

import java.time.LocalDate;
import java.util.ArrayList;

import com.method.ayakan.model.MataKuliah;
import com.method.ayakan.model.TIAkademik;
import com.method.ayakan.model.TIOrganisasi;
import com.method.ayakan.model.TKAkademik;
import com.method.ayakan.service.CatatanManager;
import com.method.ayakan.service.LinkManager;
import com.method.ayakan.service.MataKuliahManager;
import com.method.ayakan.service.TaskManager;

public class DummyDataInitializer {

    private void inisialisasiTugas() {

    }

    public static void init(
            MataKuliahManager mkManager,
            TaskManager taskManager,
            CatatanManager catatanManager,
            LinkManager linkManager) {
        mkManager.tambah("DPBO");
        mkManager.tambah("Statistika");
        mkManager.tambah("Matematika Diskrit");
        MataKuliah dpbo = mkManager.cariMatkulById(1);
        MataKuliah stat = mkManager.cariMatkulById(2);
        MataKuliah matdis = mkManager.cariMatkulById(3);

        ArrayList<String> anggotaDPBO = new ArrayList<>();
        anggotaDPBO.add("Raya");
        anggotaDPBO.add("Trye");
        anggotaDPBO.add("Aulia");
        taskManager.tambahTugas(
                new TKAkademik(
                        dpbo,
                        "Kelompok A",
                        anggotaDPBO,
                        "Tugas Besar DPBO",
                        "Membuat aplikasi",
                        false,
                        "High",
                        LocalDate.now().plusDays(5)
                )
        );

        taskManager.tambahTugas(
                new TIAkademik(
                        stat,
                        "Laporan Uji Hipotesis",
                        "Mengerjakan laporan",
                        false,
                        "Medium",
                        LocalDate.now().plusDays(4)
                )
        );

        taskManager.tambahTugas(
                new TIOrganisasi(
                        "Staff Muda HMRPL",
                        "Studi Banding",
                        "Bertatap muka",
                        false,
                        "Medium",
                        LocalDate.now().plusDays(3)
                )
        );

        taskManager.tambahTugas(
                new TIAkademik(
                        matdis,
                        "Kuis Minggu-13",
                        "Mengerjakan soal",
                        false,
                        "Low",
                        LocalDate.now().plusDays(2)
                )
        );

        // CATATAN
        catatanManager.tambah(
                matdis,
                "Sifat-sifat Graf",
                "Sirkuit Euler: \n"
                + "a. Graf terhubung\n"
                + "b. Semua simpul berderajat genap\n"
                + "c. Melewati setiap sisi tepat sekali dan kembali ke simpul awal.\n"
                + "\n"
                + "Lintasan Euler:\n"
                + "a. Graf terhubung\n"
                + "b. 2 simpul berderajat ganjil\n"
                + "c. Melewati setiap sisi tepat sekali dan tidak kembali ke simpul awal\n"
                + "\n"
                + "Sirkuit Hamilton:\n"
                + "a. Graf terhubung\n"
                + "b. Melewati setiap simpul tepat sekali dan kembali ke simpul awal\n"
                + "\n"
                + "Lintasan Hamilton:\n"
                + "a. Graf terhubung\n"
                + "b. Melewati setiap simpul tepat sekali dan tidak kembali ke simpul awal."
        );

        // LINK
        linkManager.tambah(
                dpbo,
                "Materi Encapsulation",
                "https://www.youtube.com/watch?v=eboNNUADeIc"
        );
    }
}
