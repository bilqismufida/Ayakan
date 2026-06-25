package com.method.ayakan.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Random;

public class Notif {

    private Tugas tugas;
    private String pesan;

    private static final String[] daftarPesan = {
        "Ayo Kerjakan!!!",
        "Hati-hati sama dosen killer!!",
        "Nilai anda sangat penting!!!",
        "Jangan sampai lupa submit!!!",
        "Deadline semakin dekat!!!"
    };

    public Notif(Tugas tugas) {

        this.tugas = tugas;

        Random random = new Random();

        this.pesan = daftarPesan[random.nextInt(daftarPesan.length)];
    }

    public Tugas getTugas() {
        return tugas;
    }

    public String getPesan() {
        return pesan;
    }

    public long getSisaHari() {

        return ChronoUnit.DAYS.between(
                LocalDate.now(),
                tugas.getDeadline()
        );
    }

    public boolean perluDitampilkan() {

        long sisaHari = getSisaHari();

        int batasHari;

        switch (tugas.getPriority().toLowerCase()) {

            case "high":
                batasHari = 7;
                break;

            case "medium":
                batasHari = 5;
                break;

            case "low":
                batasHari = 3;
                break;

            default:
                return false;
        }

        return sisaHari <= batasHari;
    }
}