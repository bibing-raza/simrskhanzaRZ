package bridging;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import fungsi.koneksiDB;
import fungsi.sekuel;
import java.awt.Component;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.net.ssl.SSLContext;
import javax.net.ssl.X509TrustManager;
import javax.swing.JOptionPane;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author khanzasoft
 */
public class ApiEKLAIM_inacbg {
    private Connection koneksi = koneksiDB.condb();
    private PreparedStatement ps;
    private ResultSet rs;
    private String URL = "", requestJson1 = "", requestJson2 = "", requestJson3 = "", requestJson4 = "",
            requestJson5 = "", requestJson6 = "", requestJson7 = "", requestJson8 = "", requestJson9 = "",
            requestJson10 = "", requestJson11 = "", requestJson12 = "", requestJson13 = "", requestJson14 = "",
            stringbalik = "", requestJson15 = "";
    private HttpHeaders headers;
    private HttpEntity requestEntity;
    private JsonNode root;
    private sekuel Sequel = new sekuel();
    private boolean x;
    private int i;
    private JsonNode response;
    private ObjectMapper mapper = new ObjectMapper();
    
    public ApiEKLAIM_inacbg(){
        super();
        try {
            URL = koneksiDB.URL_EKLAIM_INACBG();
        } catch (Exception e) {
            System.out.println("Notif : "+e);
        }
    }
    
    public boolean ngirimJKN(String noRawat) {
        try {
            ps = koneksi.prepareStatement("select bs.no_kartu, bs.no_sep, bs.nomr, bs.nama_pasien, "
                    + "concat(bs.tanggal_lahir,' ','00:00:00') tgl_lhr, if(p.jk='L','1','2') jk, bs.tglsep, "
                    + "bs.jnspelayanan from bridging_sep bs INNER JOIN pasien p ON p.no_rkm_medis=bs.nomr where no_rawat='" + noRawat + "'");
             try {
                rs=ps.executeQuery();
                while(rs.next()){
                    headers = new HttpHeaders();
                    headers.setContentType(MediaType.APPLICATION_JSON);
                    headers.add("Content-Type","application/json;charset=UTF-8");
                    requestJson1=
                            "{"
                                + "\"metadata\": {"
                                + "\"method\": \"new_claim\""
                            + "},"
                                    + "\"data\": {"
                                        + "\"no_rawat\": \"" + noRawat + "\","
                                        + "\"nomor_kartu\": \"" + rs.getString("no_kartu") + "\","
                                        + "\"nomor_sep\": \"" + rs.getString("no_sep") + "\","
                                        + "\"tglsep\": \"" + rs.getString("tglsep") + "\","
                                        + "\"jnspelayanan\": \"" + rs.getString("jnspelayanan") + "\","
                                        + "\"nomor_rm\": \"" + rs.getString("nomr") + "\","
                                        + "\"nama_pasien\": \"" + rs.getString("nama_pasien") + "\","
                                        + "\"tgl_lahir\": \"" + rs.getString("tgl_lhr") + "\","
                                        + "\"gender\": \"" + rs.getString("jk") + "\""
                            + "}"                                
                          + "}";
                    
                    System.out.println("JSON : " + requestJson1);
                    requestEntity = new HttpEntity(requestJson1, headers);
                    stringbalik = getRest().exchange(URL, HttpMethod.POST, requestEntity, String.class).getBody();
                    System.out.println("Output : " + stringbalik);
                    root = mapper.readTree(stringbalik);  
                 
                    if (root.path("metadata").path("code").asText().equals("200")) {
                        JOptionPane.showMessageDialog(null, root.path("metadata").path("message").asText());
                        x = true;
                    } else {
                        JOptionPane.showMessageDialog(null, root.path("metadata").path("message").asText());
                        x = false;
                    }

                }
             } catch (Exception e) {
                 System.out.println("Notif : "+e);
                 if (e.toString().contains("UnknownHostException") || e.toString().contains("false")) {
                     JOptionPane.showMessageDialog(null, e);
                 }
             } finally{
                 if(rs!=null){
                     rs.close();
                 }
                 if(ps!=null){
                     ps.close();
                 }
             }
        } catch (Exception ex) {
            System.out.println("Notifikasi : " + ex);
            if (ex.toString().contains("UnknownHostException") || ex.toString().contains("false")) {
                JOptionPane.showMessageDialog(null, ex);
            }
        }
        return x;
    }
    
    public boolean ngirimLAINYA(String norawat, String noIdentitas, String tglReg, String jnsrwt, String norm, String nmPas, String tglLhr, String jk, String payor) {
        try {
            headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.add("Content-Type", "application/json;charset=UTF-8");
            requestJson10
                    = "{"
                    + "\"metadata\": {"
                    + "\"method\": \"generate_claim_number\""                    
                    + "},"
                    + "\"data\": {"
                    + "\"no_rawat\": \"" + norawat + "\","
                    + "\"payor_id\": \"" + payor + "\""
                    + "}"
                    + "}";

            System.out.println("JSON : " + requestJson10);
            requestEntity = new HttpEntity(requestJson10, headers);
            stringbalik = getRest().exchange(URL, HttpMethod.POST, requestEntity, String.class).getBody();
            System.out.println("Output : " + stringbalik);
            root = mapper.readTree(stringbalik);

            if (root.path("metadata").path("code").asText().equals("200")) {
                //-------------------------------------------------------
                headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                headers.add("Content-Type", "application/json;charset=UTF-8");
                requestJson11
                        = "{"
                        + "\"metadata\": {"
                        + "\"method\": \"new_claim\""
                        + "},"
                        + "\"data\": {"
                        + "\"no_rawat\": \"" + norawat + "\","
                        + "\"nomor_kartu\": \"" + noIdentitas + "\","
                        + "\"nomor_sep\": \"" + root.path("metadata").path("claim_number").asText() + "\","
                        + "\"tglsep\": \"" + tglReg + "\","
                        + "\"jnspelayanan\": \"" + jnsrwt + "\","
                        + "\"nomor_rm\": \"" + norm + "\","
                        + "\"nama_pasien\": \"" + nmPas + "\","
                        + "\"tgl_lahir\": \"" + tglLhr + "\","
                        + "\"gender\": \"" + jk + "\""
                        + "}"
                        + "}";

                System.out.println("JSON : " + requestJson11);
                requestEntity = new HttpEntity(requestJson11, headers);
                stringbalik = getRest().exchange(URL, HttpMethod.POST, requestEntity, String.class).getBody();
                System.out.println("Output : " + stringbalik);
                root = mapper.readTree(stringbalik);

                if (root.path("metadata").path("code").asText().equals("200")) {
                    JOptionPane.showMessageDialog(null, root.path("metadata").path("message").asText());
                    x = true;
                } else {
                    JOptionPane.showMessageDialog(null, root.path("metadata").path("message").asText());
                    x = false;
                }
                //-------------------------------------------------------
            } else {
                JOptionPane.showMessageDialog(null, root.path("metadata").path("message").asText());
                x = false;
            }
        } catch (Exception erornya) {
            System.out.println("Notifikasi : " + erornya);
            if (erornya.toString().contains("UnknownHostException") || erornya.toString().contains("false")) {
                JOptionPane.showMessageDialog(null, erornya);
            }
        }
        return x;
    }
    
    public boolean menggrouper(String noSep, String noKartu, String tglMsk, String tglPulang, String jnsRawt, String klsRwt, String adlSub, String adlKro,
            String icuIndi, String icuLos, String venti, String classInd, String classClass, String classLos, String addPaymen, String birthWe, String dischargeStts,
            String diagnosa, String prosedur, Double pnb, Double pb, Double kon, String ta, Double kep, String pen, Double rad, Double lab, String pd, Double reh,
            Double kam, Double ri, Double obat, String okr, String oke, String alkes, String bmhp, String sa, String pj, String kj, String petiJen, String plastikErat,
            String dj, String mj,String dmj, String covidStatus, String noKT, String episod, String ccInd, String rsDarurat, String coInside, String labAL,
            String labPro, String labCRP, String labKul, String labDim, String labPT, String labAPTT, String labWP, String labAnti, String labAnaGas, String labAlbu, 
            String radTho, String tarifPE, String nmDokter, String payorI, String payorC, String cob, String codNik, String konvalesen, String naat, String isoman,
            String bayi_baru_lhr, String prosedur_inadrg, String diagnosa_inadrg) {
        try {
            headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.add("Content-Type", "application/json;charset=UTF-8");
            requestJson2=
                        "{"
                                + "\"metadata\": {"
                                + "\"method\": \"set_claim_data\","
                                + "\"nomor_sep\": \"" + noSep + "\""
                            + "},"
                                    + "\"data\": {"
                                        + "\"nomor_sep\": \"" + noSep + "\","
                                        + "\"nomor_kartu\": \"" + noKartu + "\","
                                        + "\"tgl_masuk\": \"" + tglMsk + "\","
                                        + "\"tgl_pulang\": \"" + tglPulang + "\","
                                        + "\"jenis_rawat\": \"" + jnsRawt + "\","
                                        + "\"kelas_rawat\": \"" + klsRwt + "\","
                                        + "\"adl_sub_acute\": \"" + adlSub + "\","
                                        + "\"adl_chronic\": \"" + adlKro + "\","
                                        + "\"icu_indikator\": \"" + icuIndi + "\","
                                        + "\"icu_los\": \"" + icuLos + "\","
                                        + "\"ventilator_hour\": \"" + venti + "\","
                                        + "\"upgrade_class_ind\": \"" + classInd + "\","
                                        + "\"upgrade_class_class\": \"" + classClass + "\","
                                        + "\"upgrade_class_los\": \"" + classLos + "\","
                                        + "\"add_payment_pct\": \"" + addPaymen + "\","
                                        + "\"birth_weight\": \"" + birthWe + "\","
                                        + "\"discharge_status\": \"" + dischargeStts + "\","
                                        + "\"diagnosa\": \"" + diagnosa + "\","
                                        + "\"procedure\": \"" + prosedur + "\","
                    + "\"diagnosa_inagrouper\": \"" + diagnosa_inadrg + "\","
                    + "\"procedure_inagrouper\": \"" + prosedur_inadrg + "\","
                                    + "\"tarif_rs\": {"
                                        + "\"prosedur_non_bedah\": \"" + pnb + "\","
                                        + "\"prosedur_bedah\": \"" + pb + "\","
                                        + "\"konsultasi\": \"" + kon + "\","
                                        + "\"tenaga_ahli\": \"" + ta + "\","
                                        + "\"keperawatan\": \"" + kep + "\","
                                        + "\"penunjang\": \"" + pen + "\","
                                        + "\"radiologi\": \"" + rad + "\","
                                        + "\"laboratorium\": \"" + lab + "\","
                                        + "\"pelayanan_darah\": \"" + pd + "\","
                                        + "\"rehabilitasi\": \"" + reh + "\","
                                        + "\"kamar\": \"" + kam + "\","
                                        + "\"rawat_intensif\": \"" + ri + "\","
                                        + "\"obat\": \"" + obat + "\","
                                        + "\"obat_kronis\": \"" + okr + "\","
                                        + "\"obat_kemoterapi\": \"" + oke + "\","
                                        + "\"alkes\": \"" + alkes + "\","
                                        + "\"bmhp\": \"" + bmhp + "\","
                                        + "\"sewa_alat\": \"" + sa + "\""
                                    + "},"
                                        + "\"pemulasaraan_jenazah\": \"" + pj + "\","
                                        + "\"kantong_jenazah\": \"" + kj + "\","
                                        + "\"peti_jenazah\": \"" + petiJen + "\","
                                        + "\"plastik_erat\": \"" + plastikErat + "\","
                                        + "\"desinfektan_jenazah\": \"" + dj + "\","
                                        + "\"mobil_jenazah\": \"" + mj + "\","
                                        + "\"desinfektan_mobil_jenazah\": \"" + dmj + "\","
                                        + "\"covid19_status_cd\": \"" + covidStatus + "\","
                                        + "\"nomor_kartu_t\": \"" + noKT + "\","
                                        + "\"episodes\": \"" + episod + "\","   
                                        + "\"covid19_cc_ind\": \"" + ccInd + "\","
                                        + "\"covid19_rs_darurat_ind\": \"" + rsDarurat + "\","
                                        + "\"covid19_co_insidense_ind\": \"" + coInside + "\","
                                    + "\"covid19_penunjang_pengurang\": {"
                                        + "\"lab_asam_laktat\": \"" + labAL + "\","
                                        + "\"lab_procalcitonin\": \"" + labPro + "\","
                                        + "\"lab_crp\": \"" + labCRP + "\","
                                        + "\"lab_kultur\": \"" + labKul + "\","
                                        + "\"lab_d_dimer\": \"" + labDim + "\","
                                        + "\"lab_pt\": \"" + labPT + "\","
                                        + "\"lab_aptt\": \"" + labAPTT + "\","
                                        + "\"lab_waktu_pendarahan\": \"" + labWP + "\","
                                        + "\"lab_anti_hiv\": \"" + labAnti + "\","
                                        + "\"lab_analisa_gas\": \"" + labAnaGas + "\","
                                        + "\"lab_albumin\": \"" + labAlbu + "\","
                                        + "\"rad_thorax_ap_pa\": \"" + radTho + "\""
                                    + "},"
                                        + "\"terapi_konvalesen\": \"" + konvalesen + "\","
                                        + "\"akses_naat\": \"" + naat + "\","
                                        + "\"isoman_ind\": \""+isoman+"\","
                                        + "\"bayi_lahir_status_cd\": \"" + bayi_baru_lhr + "\","                    
                                        + "\"tarif_poli_eks\": \"" + tarifPE + "\","
                                        + "\"nama_dokter\": \"" + nmDokter + "\","
                                        + "\"kode_tarif\": \"BP\","
                                        + "\"payor_id\": \"" + payorI + "\","
                                        + "\"payor_cd\": \"" + payorC + "\","
                                        + "\"cob_cd\": \"" + cob + "\","
                                        + "\"coder_nik\": \"" + codNik + "\""
                            + "}"                                
                          + "}";

            System.out.println("JSON : " + requestJson2);
            requestEntity = new HttpEntity(requestJson2, headers);
            stringbalik = getRest().exchange(URL, HttpMethod.POST, requestEntity, String.class).getBody();
            System.out.println("Output : " + stringbalik);
            root = mapper.readTree(stringbalik);

            if (root.path("metadata").path("code").asText().equals("200")) {
                i = JOptionPane.showConfirmDialog(null, "" + root.path("metadata").path("message").asText()
                        + ". Apakah proses ini tetap akan dilanjutkan..?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
                if (i == JOptionPane.YES_OPTION) {
                    //-------------------------------------------
                    try {
                        headers = new HttpHeaders();
                        headers.setContentType(MediaType.APPLICATION_JSON);
                        headers.add("Content-Type", "application/json;charset=UTF-8");
                        requestJson3
                                = "{"
                                + "\"metadata\": {"
                                + "\"method\": \"grouper\","
                                + "\"stage\": \"1\""
                                + "},"
                                + "\"data\": {"
                                + "\"nomor_sep\": \"" + noSep + "\""
                                + "}"
                                + "}";

                        System.out.println("JSON : " + requestJson3);
                        requestEntity = new HttpEntity(requestJson3, headers);
                        stringbalik = getRest().exchange(URL, HttpMethod.POST, requestEntity, String.class).getBody();
                        System.out.println("Output : " + stringbalik);
                        root = mapper.readTree(stringbalik);

                        if (root.path("metadata").path("code").asText().equals("200")) {
                            JOptionPane.showMessageDialog(null, root.path("metadata").path("message").asText());
                            x = true;
                        } else {
                            JOptionPane.showMessageDialog(null, root.path("metadata").path("message").asText());
                            x = false;
                        }
                    } catch (Exception erornya) {
                        System.out.println("Notifikasi : " + erornya);
                        if (erornya.toString().contains("UnknownHostException") || erornya.toString().contains("false")) {
                            JOptionPane.showMessageDialog(null, erornya);
                        }
                    }
                    //-------------------------------------------
                }
            } else {
                JOptionPane.showMessageDialog(null, root.path("metadata").path("message").asText());                
            }
        } catch (Exception ex) {
            System.out.println("Notifikasi : " + ex);
            if (ex.toString().contains("UnknownHostException") || ex.toString().contains("false")) {
                JOptionPane.showMessageDialog(null, ex);
            }
        }
        return x;
    }
    
    public void menggrouperKedua(String nosep_pengajuan, String kodeTopUP) {
        try {
            headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.add("Content-Type", "application/json;charset=UTF-8");
            requestJson4
                    = "{"
                    + "\"metadata\": {"
                    + "\"method\": \"grouper\","
                    + "\"stage\": \"2\""
                    + "},"
                    + "\"data\": {"
                    + "\"nomor_sep\": \"" + nosep_pengajuan + "\","
                    + "\"special_cmg\": \"" + kodeTopUP + "\""
                    + "}"
                    + "}";

            System.out.println("JSON : " + requestJson4);
            requestEntity = new HttpEntity(requestJson4, headers);
            stringbalik = getRest().exchange(URL, HttpMethod.POST, requestEntity, String.class).getBody();
            System.out.println("Output : " + stringbalik);
            root = mapper.readTree(stringbalik);

            if (root.path("metadata").path("code").asText().equals("200")) {
                JOptionPane.showMessageDialog(null, root.path("metadata").path("message").asText());                
            } else {
                JOptionPane.showMessageDialog(null, root.path("metadata").path("message").asText());                
            }
        } catch (Exception erornya) {
            System.out.println("Notifikasi : " + erornya);
            if (erornya.toString().contains("UnknownHostException") || erornya.toString().contains("false")) {
                JOptionPane.showMessageDialog(null, erornya);
            }
        }
    }
    
    public void mempinal(String nosep_pengajuan, String nikKoder) {
        try {
            headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.add("Content-Type", "application/json;charset=UTF-8");
            requestJson5
                    = "{"
                    + "\"metadata\": {"
                    + "\"method\": \"claim_final\""                    
                    + "},"
                    + "\"data\": {"
                    + "\"nomor_sep\": \"" + nosep_pengajuan + "\","
                    + "\"coder_nik\": \"" + nikKoder + "\""
                    + "}"
                    + "}";

            System.out.println("JSON : " + requestJson5);
            requestEntity = new HttpEntity(requestJson5, headers);
            stringbalik = getRest().exchange(URL, HttpMethod.POST, requestEntity, String.class).getBody();
            System.out.println("Output : " + stringbalik);
            root = mapper.readTree(stringbalik);

            if (root.path("metadata").path("code").asText().equals("200")) {
                JOptionPane.showMessageDialog(null, root.path("metadata").path("message").asText());                
            } else {
                JOptionPane.showMessageDialog(null, root.path("metadata").path("message").asText());                
            }
        } catch (Exception erornya) {
            System.out.println("Notifikasi : " + erornya);
            if (erornya.toString().contains("UnknownHostException") || erornya.toString().contains("false")) {
                JOptionPane.showMessageDialog(null, erornya);
            }
        }
    }
    
    public void mengedit(String nosep_pengajuan) {
        try {
            headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.add("Content-Type", "application/json;charset=UTF-8");
            requestJson6
                    = "{"
                    + "\"metadata\": {"
                    + "\"method\": \"reedit_claim\""                    
                    + "},"
                    + "\"data\": {"
                    + "\"nomor_sep\": \"" + nosep_pengajuan + "\""
                    + "}"
                    + "}";

            System.out.println("JSON : " + requestJson6);
            requestEntity = new HttpEntity(requestJson6, headers);
            stringbalik = getRest().exchange(URL, HttpMethod.POST, requestEntity, String.class).getBody();
            System.out.println("Output : " + stringbalik);
            root = mapper.readTree(stringbalik);

            if (root.path("metadata").path("code").asText().equals("200")) {
                JOptionPane.showMessageDialog(null, root.path("metadata").path("message").asText());                
            } else {
                JOptionPane.showMessageDialog(null, root.path("metadata").path("message").asText());                
            }
        } catch (Exception erornya) {
            System.out.println("Notifikasi : " + erornya);
            if (erornya.toString().contains("UnknownHostException") || erornya.toString().contains("false")) {
                JOptionPane.showMessageDialog(null, erornya);
            }
        }
    }
    
    public void menghapus(String nosep_pengajuan, String nikKoder) {
        try {
            headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.add("Content-Type", "application/json;charset=UTF-8");
            requestJson7
                    = "{"
                    + "\"metadata\": {"
                    + "\"method\": \"delete_claim\""                    
                    + "},"
                    + "\"data\": {"
                    + "\"nomor_sep\": \"" + nosep_pengajuan + "\","
                    + "\"coder_nik\": \"" + nikKoder + "\""
                    + "}"
                    + "}";

            System.out.println("JSON : " + requestJson7);
            requestEntity = new HttpEntity(requestJson7, headers);
            stringbalik = getRest().exchange(URL, HttpMethod.POST, requestEntity, String.class).getBody();
            System.out.println("Output : " + stringbalik);
            root = mapper.readTree(stringbalik);

            if (root.path("metadata").path("code").asText().equals("200")) {
                JOptionPane.showMessageDialog(null, root.path("metadata").path("message").asText());                
            } else {
                JOptionPane.showMessageDialog(null, root.path("metadata").path("message").asText());                
            }
        } catch (Exception erornya) {
            System.out.println("Notifikasi : " + erornya);
            if (erornya.toString().contains("UnknownHostException") || erornya.toString().contains("false")) {
                JOptionPane.showMessageDialog(null, erornya);
            }
        }
    }
    
    public void mengirimOnline(String nosep_pengajuan) {
        try {
            headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.add("Content-Type", "application/json;charset=UTF-8");
            requestJson8
                    = "{"
                    + "\"metadata\": {"
                    + "\"method\": \"send_claim_individual\""                    
                    + "},"
                    + "\"data\": {"
                    + "\"nomor_sep\": \"" + nosep_pengajuan + "\""
                    + "}"
                    + "}";

            System.out.println("JSON : " + requestJson8);
            requestEntity = new HttpEntity(requestJson8, headers);
            stringbalik = getRest().exchange(URL, HttpMethod.POST, requestEntity, String.class).getBody();
            System.out.println("Output : " + stringbalik);
            root = mapper.readTree(stringbalik);

            if (root.path("metadata").path("code").asText().equals("200")) {
                JOptionPane.showMessageDialog(null, root.path("metadata").path("message").asText());                
            } else {
                JOptionPane.showMessageDialog(null, root.path("metadata").path("message").asText());                
            }
        } catch (Exception erornya) {
            System.out.println("Notifikasi : " + erornya);
            if (erornya.toString().contains("UnknownHostException") || erornya.toString().contains("false")) {
                JOptionPane.showMessageDialog(null, erornya);
            }
        }
    }
    
    public boolean ngecekDiagnosa(String kodeICD) {
        try {
            headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.add("Content-Type", "application/json;charset=UTF-8");
            requestJson9
                    = "{"
                    + "\"metadata\": {"
                    + "\"method\": \"search_diagnosis\""                    
                    + "},"
                    + "\"data\": {"
                    + "\"keyword\": \"" + kodeICD + "\""
                    + "}"
                    + "}";

            System.out.println("JSON : " + requestJson9);
            requestEntity = new HttpEntity(requestJson9, headers);
            stringbalik = getRest().exchange(URL, HttpMethod.POST, requestEntity, String.class).getBody();
            System.out.println("Output : " + stringbalik);
            root = mapper.readTree(stringbalik);

            if (root.path("metadata").path("code").asText().equals("200") && root.path("response").path("count").asText().equals("1")) {
                x = true;
            } else {
                x = false;
            }
        } catch (Exception erornya) {
            System.out.println("Notifikasi : " + erornya);
            if (erornya.toString().contains("UnknownHostException") || erornya.toString().contains("false")) {
                JOptionPane.showMessageDialog(null, erornya);
            }
        }
        return x;
    }
    
    public boolean ngecekDiagnosaINADRG(String kodeICD) {
        try {
            headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.add("Content-Type", "application/json;charset=UTF-8");
            requestJson15
                    = "{"
                    + "\"metadata\": {"
                    + "\"method\": \"search_diagnosis_inagrouper\""                    
                    + "},"
                    + "\"data\": {"
                    + "\"keyword\": \"" + kodeICD + "\""
                    + "}"
                    + "}";

            System.out.println("JSON : " + requestJson15);
            requestEntity = new HttpEntity(requestJson15, headers);
            stringbalik = getRest().exchange(URL, HttpMethod.POST, requestEntity, String.class).getBody();
            System.out.println("Output : " + stringbalik);
            root = mapper.readTree(stringbalik);

            if (root.path("metadata").path("code").asText().equals("200") && root.path("response").path("count").asText().equals("1")) {
                x = true;
            } else {
                x = false;
            }
        } catch (Exception erornya) {
            System.out.println("Notifikasi : " + erornya);
            if (erornya.toString().contains("UnknownHostException") || erornya.toString().contains("false")) {
                JOptionPane.showMessageDialog(null, erornya);
            }
        }
        return x;
    }
    
    public void mengunggahFile(String noPengajuan, String jnsDokumen, String filenya, String enkodePDF) {
        try {
            headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.add("Content-Type", "application/json;charset=UTF-8");
            requestJson12
                    = "{"
                    + "\"metadata\": {"
                    + "\"method\": \"file_upload\","
                    + "\"nomor_sep\": \"" + noPengajuan + "\","
                    + "\"file_class\": \"" + jnsDokumen + "\","
                    + "\"file_name\": \"" + filenya + "\""
                    + "},"
                    + "\"data\":\"" + enkodePDF + "\""
                    + "}";

            System.out.println("JSON : " + requestJson12);
            requestEntity = new HttpEntity(requestJson12, headers);
            stringbalik = getRest().exchange(URL, HttpMethod.POST, requestEntity, String.class).getBody();
//            System.out.println("Output : " + stringbalik);
            root = mapper.readTree(stringbalik);

//            if (root.path("metadata").path("code").asText().equals("200")) {
//                JOptionPane.showMessageDialog(null, root.path("metadata").path("message").asText()); 
//            } else {
//                JOptionPane.showMessageDialog(null, root.path("metadata").path("message").asText()); 
//            }
        } catch (Exception erornya) {
            System.out.println("Notifikasi : " + erornya);
            if (erornya.toString().contains("UnknownHostException") || erornya.toString().contains("false")) {
                JOptionPane.showMessageDialog(null, erornya);
            }
        }
    }
    
    public void mengambilData(String norawt, String nosep_pengajuan, String tglSEP) {
        try {
            headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.add("Content-Type", "application/json;charset=UTF-8");
            requestJson13
                    = "{"
                    + "\"metadata\": {"
                    + "\"method\": \"get_claim_data\""                    
                    + "},"
                    + "\"data\": {"
                    + "\"no_rawat\": \"" + norawt + "\","
                    + "\"nomor_sep\": \"" + nosep_pengajuan + "\","
                    + "\"tglsep\": \"" + tglSEP + "\""
                    + "}"
                    + "}";

            System.out.println("JSON : " + requestJson13);
            requestEntity = new HttpEntity(requestJson13, headers);
            stringbalik = getRest().exchange(URL, HttpMethod.POST, requestEntity, String.class).getBody();
            System.out.println("Output : " + stringbalik);
            root = mapper.readTree(stringbalik);

            if (root.path("metadata").path("code").asText().equals("200")) {
                JOptionPane.showMessageDialog(null, root.path("metadata").path("message").asText()); 
            } else {
                JOptionPane.showMessageDialog(null, root.path("metadata").path("message").asText()); 
            }
        } catch (Exception erornya) {
            System.out.println("Notifikasi : " + erornya);
            if (erornya.toString().contains("UnknownHostException") || erornya.toString().contains("false")) {
                JOptionPane.showMessageDialog(null, erornya);
            }
        }
    }
    
    public void menghapusFileUpload(String nosep_pengajuan, String kodeFile) {
        try {
            headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.add("Content-Type", "application/json;charset=UTF-8");
            requestJson14
                    = "{"
                    + "\"metadata\": {"
                    + "\"method\": \"file_delete\""                    
                    + "},"
                    + "\"data\": {"
                    + "\"nomor_sep\": \"" + nosep_pengajuan + "\","
                    + "\"file_id\": \"" + kodeFile + "\""
                    + "}"
                    + "}";

            System.out.println("JSON : " + requestJson14);
            requestEntity = new HttpEntity(requestJson14, headers);
            stringbalik = getRest().exchange(URL, HttpMethod.POST, requestEntity, String.class).getBody();
            System.out.println("Output : " + stringbalik);
            root = mapper.readTree(stringbalik);

            if (root.path("metadata").path("code").asText().equals("200")) {
                JOptionPane.showMessageDialog(null, root.path("metadata").path("message").asText()); 
            } else {
                JOptionPane.showMessageDialog(null, root.path("metadata").path("message").asText()); 
            }
        } catch (Exception erornya) {
            System.out.println("Notifikasi : " + erornya);
            if (erornya.toString().contains("UnknownHostException") || erornya.toString().contains("false")) {
                JOptionPane.showMessageDialog(null, erornya);
            }
        }
    }
    
    public RestTemplate getRest() throws NoSuchAlgorithmException, KeyManagementException {
        SSLContext sslContext = SSLContext.getInstance("SSL");
        javax.net.ssl.TrustManager[] trustManagers= {
            new X509TrustManager() {
                public X509Certificate[] getAcceptedIssuers() {return null;}
                public void checkServerTrusted(X509Certificate[] arg0, String arg1)throws CertificateException {}
                public void checkClientTrusted(X509Certificate[] arg0, String arg1)throws CertificateException {}
            }
        };
        sslContext.init(null,trustManagers , new SecureRandom());
        SSLSocketFactory sslFactory=new SSLSocketFactory(sslContext,SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
        Scheme scheme=new Scheme("https",443,sslFactory);
        HttpComponentsClientHttpRequestFactory factory=new HttpComponentsClientHttpRequestFactory();
        factory.getHttpClient().getConnectionManager().getSchemeRegistry().register(scheme);
        return new RestTemplate(factory);
    }    
}