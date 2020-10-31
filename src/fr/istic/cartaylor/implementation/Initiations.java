package fr.istic.cartaylor.implementation;

import fr.istic.cartaylor.api.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
/**
 * @author Arnaud Akoto <yao-arnaud.akoto@etudiant.univ-rennes1.fr>
 * @author Anthony Amiard <anthony.amiard@etudiant.univ-rennes1.fr>
 *        Classe comportant l'enssemble des initialisations du projet.
 */
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

    private HashMap<Category, Set<PartType>> variants = new HashMap<>(){{
        put(engine,engPartTypes);
        put(transmission,transPartTypes);
        put(exterior,exPartTypes);
        put(interior,inPartTypes);
    }};

    public HashMap<Category, Set<PartType>> getVariants() {
        return variants;
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
    //ensemble d'incompatibilities
    private HashMap<PartType, Set<PartType>> incompatibilities = new HashMap<>(){{
        put(xs,incForXs);
        put(is,incForIs);
        put(ta5,incForTa5);
        put(tsf7,incForTsf7);
        put(xc,incForXc);
        put(xm,incForXm);
    }};

    public HashMap<PartType, Set<PartType>> getIncompatibilities() {
        return incompatibilities;
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

    //ensemble de requirements
    private HashMap<PartType, Set<PartType>> requirements = new HashMap<>(){{
        put(eh120,reqForEh120);
        put(tc120,reqForTc120);
        put(xs,reqForXs);
        put(is,reqForIs);
    }};

    public HashMap<PartType, Set<PartType>> getRequirements() {
        return requirements;
    }

    //Methodes pour acceder aux categories et aux partTypes
    public Category accessToCategory (String categoryName){
        CategoryImpl category = new CategoryImpl(categoryName);
        if (categories.contains(category)) return  category ;
        else return  null ;
    }

    public PartType accessToPartType (String partTypeName){
        for (Category category : categories){
            Set<PartType> variants1 = this.variants.get(category);
            for (PartType part : variants1){
                if (part.getName().equals(partTypeName)) return part;
            }
        }
        return  null ;
    }


}
