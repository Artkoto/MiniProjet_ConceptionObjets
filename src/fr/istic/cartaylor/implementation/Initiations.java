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
    Set<PartType> incForEg100 = new HashSet<PartType>(){{}};
    Set<PartType> incForEg133 = new HashSet<PartType>(){{}};
    Set<PartType> incForEg2010 = new HashSet<PartType>(){{}};
    Set<PartType> incForEd110 = new HashSet<PartType>(){{}};
    Set<PartType> incForEd180 = new HashSet<PartType>(){{}};
    Set<PartType> incForEh120 = new HashSet<PartType>(){{}};
    Set<PartType> incForTm5 = new HashSet<PartType>(){{}};
    Set<PartType> incForTm6 = new HashSet<PartType>(){{}};
    Set<PartType> incForTa5 = new HashSet<PartType>(){{
        add(eg100);
    }};
    Set<PartType> incForTs6 = new HashSet<PartType>(){{}};
    Set<PartType> incForTsf7 = new HashSet<PartType>(){{
        add(eg100);
        add(eg133);
        add(ed110);
    }};
    Set<PartType> incForTc120 = new HashSet<PartType>(){{}};
    Set<PartType> incForXc = new HashSet<PartType>(){{
        add(eg210);
    }};
    Set<PartType> incForXm = new HashSet<PartType>(){{
        add(eg100);
    }};
    Set<PartType> incForXs = new  HashSet<PartType>(){{
        add(eg100);
    }};
    Set<PartType> incForIn = new HashSet<PartType>(){{}};
    Set<PartType> incForIh = new HashSet<PartType>(){{}};
    Set<PartType> incForIs = new HashSet<PartType>(){{
        add(eg100);
        add(tm5);
    }};

    public Set<PartType> getIncForEg100() {
        return incForEg100;
    }

    public Set<PartType> getIncForEg133() {
        return incForEg133;
    }

    public Set<PartType> getIncForEg2010() {
        return incForEg2010;
    }

    public Set<PartType> getIncForEd110() {
        return incForEd110;
    }

    public Set<PartType> getIncForEd180() {
        return incForEd180;
    }

    public Set<PartType> getIncForEh120() {
        return incForEh120;
    }

    public Set<PartType> getIncForTm5() {
        return incForTm5;
    }

    public Set<PartType> getIncForTm6() {
        return incForTm6;
    }

    public Set<PartType> getIncForTa5() {
        return incForTa5;
    }

    public Set<PartType> getIncForTs6() {
        return incForTs6;
    }

    public Set<PartType> getIncForTsf7() {
        return incForTsf7;
    }

    public Set<PartType> getIncForTc120() {
        return incForTc120;
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

    public Set<PartType> getIncForIn() {
        return incForIn;
    }

    public Set<PartType> getIncForIh() {
        return incForIh;
    }

    public Set<PartType> getIncForIs() {
        return incForIs;
    }

    //les requirements
    Set<PartType> reqForEg100 = new HashSet<PartType>(){{}};
    Set<PartType> reqForEg133 = new HashSet<PartType>(){{}};
    Set<PartType> reqForEg2010 = new HashSet<PartType>(){{}};
    Set<PartType> reqForEd110 = new HashSet<PartType>(){{}};
    Set<PartType> reqForEd180 = new HashSet<PartType>(){{}};
    Set<PartType> reqForEh120 = new HashSet<PartType>(){{
        add(tc120);
    }};
    Set<PartType> reqForTm5 = new HashSet<PartType>(){{}};
    Set<PartType> reqForTm6 = new HashSet<PartType>(){{}};
    Set<PartType> reqForTa5 = new HashSet<PartType>(){{}};
    Set<PartType> reqForTs6 = new HashSet<PartType>(){{}};
    Set<PartType> reqForTsf7 = new HashSet<PartType>(){{}};
    Set<PartType> reqForTc120 = new HashSet<PartType>(){{
        add(eh120);
    }};
    Set<PartType> reqForXc = new HashSet<PartType>(){{}};
    Set<PartType> reqForXm = new HashSet<PartType>(){{}};
    Set<PartType> reqForXs = new  HashSet<PartType>(){{
        add(is);
    }};
    Set<PartType> reqForIn = new HashSet<PartType>(){{}};
    Set<PartType> reqForIh = new HashSet<PartType>(){{}};
    Set<PartType> reqForIs = new HashSet<PartType>(){{
        add(xs);
    }};

    public Set<PartType> getReqForEg100() {
        return reqForEg100;
    }

    public Set<PartType> getReqForEg133() {
        return reqForEg133;
    }

    public Set<PartType> getReqForEg2010() {
        return reqForEg2010;
    }

    public Set<PartType> getReqForEd110() {
        return reqForEd110;
    }

    public Set<PartType> getReqForEd180() {
        return reqForEd180;
    }

    public Set<PartType> getReqForEh120() {
        return reqForEh120;
    }

    public Set<PartType> getReqForTm5() {
        return reqForTm5;
    }

    public Set<PartType> getReqForTm6() {
        return reqForTm6;
    }

    public Set<PartType> getReqForTa5() {
        return reqForTa5;
    }

    public Set<PartType> getReqForTs6() {
        return reqForTs6;
    }

    public Set<PartType> getReqForTsf7() {
        return reqForTsf7;
    }

    public Set<PartType> getReqForTc120() {
        return reqForTc120;
    }

    public Set<PartType> getReqForXc() {
        return reqForXc;
    }

    public Set<PartType> getReqForXm() {
        return reqForXm;
    }

    public Set<PartType> getReqForXs() {
        return reqForXs;
    }

    public Set<PartType> getReqForIn() {
        return reqForIn;
    }

    public Set<PartType> getReqForIh() {
        return reqForIh;
    }

    public Set<PartType> getReqForIs() {
        return reqForIs;
    }
}
