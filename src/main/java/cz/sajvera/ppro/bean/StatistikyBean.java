package cz.sajvera.ppro.bean;

import cz.sajvera.ppro.dao.ReceptDao;
import cz.sajvera.ppro.model.Recept;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class StatistikyBean implements Serializable {

    @Inject
    private ReceptDao receptDao;

    @Inject
    private PrihlaseniOdhlaseniBean prihlaseniOdhlaseniBean;

    private List<Recept> recepty;
    private int pocetReceptu;
    private String posledniRecept;

    @PostConstruct
    public void init() {
        recepty = prihlaseniOdhlaseniBean.getUzivatel().getRecepty();
        pocetReceptu = pocetReceptu();
        posledniRecept = posledniPridanyRecept();
    }

    public List<Recept> getRecepty() {
        return recepty;
    }

    public void setRecepty(List<Recept> recepty) {
        this.recepty = recepty;
    }

    public PrihlaseniOdhlaseniBean getPrihlaseniOdhlaseniBean() {
        return prihlaseniOdhlaseniBean;
    }

    public void setPrihlaseniOdhlaseniBean(PrihlaseniOdhlaseniBean prihlaseniOdhlaseniBean) {
        this.prihlaseniOdhlaseniBean = prihlaseniOdhlaseniBean;
    }

    public int getPocetReceptu() {
        return pocetReceptu;
    }

    public String getPosledniRecept() {
        return posledniRecept;
    }

    public int pocetReceptu() {
        if(recepty.isEmpty())
            return 0;
        else
            return recepty.size();
    }

    public String posledniPridanyRecept() {
        if(recepty.isEmpty())
            return "Žádné přidané recepty.";
        else
            return recepty.get(0).getNazev();
    }
}
