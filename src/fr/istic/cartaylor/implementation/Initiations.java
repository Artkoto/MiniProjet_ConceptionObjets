package fr.istic.cartaylor.implementation;

import fr.istic.cartaylor.api.*;

import java.util.HashSet;
import java.util.Set;

public class Initiations {
    //Categories
    private CategoryImpl engine = new CategoryImpl("Engine");
    private CategoryImpl transmission = new CategoryImpl("Transmission");
    private CategoryImpl exterior = new CategoryImpl("Exterior");
    private CategoryImpl interior = new CategoryImpl("Interior");
    private Set<Category> categories = new HashSet<Category>() {{
        add(engine);
        add(transmission);
        add(exterior);
        add(interior);
    }};

    public Set<Category> getCategories() {
        return categories;
    }

    //PartType de la categorie engine
    private PartType eg100 = new PartTypeImpl("EG100", engine);
    private PartType eg133 = new PartTypeImpl("EG133", engine);
    private PartType eg210 = new PartTypeImpl("EG210", engine);
    private PartType ed110 = new PartTypeImpl("ED110", engine);
    private PartType ed180 = new PartTypeImpl("ED180", engine);
    private PartType eh120 = new PartTypeImpl("EH120", engine);
    private Set<PartType> engPartTypes = new HashSet<PartType>(){{
        add(eg100);
        add(eg133);
        add(eg210);
        add(ed110);
        add(ed180);
        add(eh120);
    }};

    public Set<PartType> getEngPartTypes(){
        return  engPartTypes ;
    }

    //partType de la categorie transmission
    private PartType tm5 = new PartTypeImpl ("TM5", transmission);
    private PartType tm6 = new PartTypeImpl ("TM6", transmission);
    private PartType ta5 = new PartTypeImpl ("TA5", transmission);
    private PartType ts6 = new PartTypeImpl ("TS6", transmission);
    private PartType tsf7 = new PartTypeImpl ("TSF7", transmission);
    private PartType tc120 = new PartTypeImpl ("TC120", transmission);
    private  Set<PartType> transPartTypes = new HashSet<PartType>(){{
       add(tm5);
       add(tm6);
       add(ta5);
       add(ts6);
       add(tsf7);
       add(tc120);
    }};

    public Set<PartType> getTransPartTypes(){
        return  transPartTypes ;
    }

    //partType de la categorie exterior
    private PartType xc = new PartTypeImpl("XC",exterior);
    private PartType xm = new PartTypeImpl("XM",exterior);
    private PartType xs = new PartTypeImpl("XS",exterior);
    private Set<PartType> exPartTypes = new HashSet<PartType>(){{
       add(xc);
       add(xm);
       add(xs);
    }};

    public Set<PartType> getExPartTypes(){
        return  exPartTypes ;
    }

    //PartType de categorie interior
    private PartType in = new PartTypeImpl("IN", interior);
    private PartType ih = new PartTypeImpl("IH", interior);
    private PartType is = new PartTypeImpl("IS", interior);
    private Set<PartType> inPartTypes = new HashSet<PartType>(){{
       add(in);
       add(ih);
       add(is);
    }};

    public Set<PartType> getInPartTypes(){
        return  inPartTypes ;
    }

    //les incompatibilities
    Set<PartType> incForTa5 = new HashSet<PartType>(){{
        add(eg100);
    }};
    Set<PartType> incForTsf7 = new HashSet<PartType>(){{
        add(eg100);
        add(eg133);
        add(ed110);
    }};
    Set<PartType> incForXc = new HashSet<PartType>(){{
        add(eg210);
    }};
    Set<PartType> incForXm = new HashSet<PartType>(){{
        add(eg100);
    }};
    Set<PartType> incForXs = new  HashSet<PartType>(){{
        add(eg100);
    }};
    Set<PartType> incForIs = new HashSet<PartType>(){{
        add(eg100);
        add(tm5);
    }};

    public Set<PartType> getIncForTa5() {
        return incForTa5;
    }

    public Set<PartType> getIncForTsf7() {
        return incForTsf7;
    }

    public Set<PartType> getIncForXc() {
        return incForXc;
    }

    public Set<PartType> getIncForXm() {
        return incForXm;
    }

    public Set<PartType> getIncForXs() {
        return incForXs;
    }

    public Set<PartType> getIncForIs() {
        return incForIs;
    }

    //les requirements
    Set<PartType> reqForEh120 = new HashSet<PartType>(){{
        add(tc120);
    }};
   Set<PartType> reqForTc120 = new HashSet<PartType>(){{
        add(eh120);
    }};
    Set<PartType> reqForXs = new  HashSet<PartType>(){{
        add(is);
    }};
   Set<PartType> reqForIs = new HashSet<PartType>(){{
        add(xs);
    }};

    public Set<PartType> getReqForEh120() {
        return reqForEh120;
    }

    public Set<PartType> getReqForTc120() {
        return reqForTc120;
    }

    public Set<PartType> getReqForXs() {
        return reqForXs;
    }

    public Set<PartType> getReqForIs() {
        return reqForIs;
    }
}
