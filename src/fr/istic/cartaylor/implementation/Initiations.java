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

    // Engine classes
    public final class EG100 extends PartImpl {}
    public final class EG133 extends PartImpl {}
    public final class EG210 extends PartImpl {}
    public final class ED110 extends PartImpl {}
    public final class ED180 extends PartImpl {}
    public final class EH120 extends PartImpl {}

    // Engine part types
    private PartType eg100 = new PartTypeImpl(EG100.class, engine);
    private PartType eg133 = new PartTypeImpl(EG133.class, engine);
    private PartType eg210 = new PartTypeImpl(EG210.class, engine);
    private PartType ed110 = new PartTypeImpl(ED110.class, engine);
    private PartType ed180 = new PartTypeImpl(ED180.class, engine);
    private PartType eh120 = new PartTypeImpl(EH120.class, engine);
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

    // Transmission classes
    public final class TM5 extends PartImpl {}
    public final class TM6 extends PartImpl {}
    public final class TA5 extends PartImpl {}
    public final class TS6 extends PartImpl {}
    public final class TSF7 extends PartImpl {}
    public final class TC120 extends PartImpl {}

    // Transmission part types
    private PartType tm5 = new PartTypeImpl (TM5.class, transmission);
    private PartType tm6 = new PartTypeImpl (TM6.class, transmission);
    private PartType ta5 = new PartTypeImpl (TA5.class, transmission);
    private PartType ts6 = new PartTypeImpl (TS6.class, transmission);
    private PartType tsf7 = new PartTypeImpl (TSF7.class, transmission);
    private PartType tc120 = new PartTypeImpl (TC120.class, transmission);
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

    // Exterior classes
    public final class XC extends PartImpl {}
    public final class XM extends PartImpl {}
    public final class XS extends PartImpl {}

    //Exterior part types
    private PartType xc = new PartTypeImpl(XC.class,exterior);
    private PartType xm = new PartTypeImpl(XM.class,exterior);
    private PartType xs = new PartTypeImpl(XS.class,exterior);
    private Set<PartType> exPartTypes = new HashSet<PartType>(){{
       add(xc);
       add(xm);
       add(xs);
    }};

    public Set<PartType> getExPartTypes(){
        return  exPartTypes ;
    }

    // Interior classes
    public final class IN extends PartImpl {}
    public final class IH extends PartImpl {}
    public final class IS extends PartImpl {}

    //PartType de categorie interior
    private PartType in = new PartTypeImpl(IN.class, interior);
    private PartType ih = new PartTypeImpl(IH.class, interior);
    private PartType is = new PartTypeImpl(IS.class, interior);
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
