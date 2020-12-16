package fr.istic.cartaylor.implementation;

import fr.istic.cartaylor.api.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
/**
 * @author Arnaud Akoto <yao-arnaud.akoto@etudiant.univ-rennes1.fr>
 * @author Anthony Amiard <anthony.amiard@etudiant.univ-rennes1.fr>
 *
 * Class initializing part types, incompatibilities and requirement.
 */
public class Initiations implements Initializer {

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

    /** Class for EG100 engine parts */
    public final class EG100 extends PartImpl {}
    /** Class for EG133 engine parts */
    public final class EG133 extends PartImpl {}
    /** Class for EG210 engine parts */
    public final class EG210 extends PartImpl {}
    /** Class for ED110 engine parts */
    public final class ED110 extends PartImpl {}
    /** Class for ED180 engine parts */
    public final class ED180 extends PartImpl {}
    /** Class for EH120 engine parts */
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

    /**
     * Returns a set of engine part types.
     * @return Set of engine part types
     */
    public Set<PartType> getEngPartTypes(){
        return  engPartTypes ;
    }

    // Transmission classes
    /** Class for TM5 transmission parts */
    public final class TM5 extends PartImpl {}
    /** Class for TM6 transmission parts */
    public final class TM6 extends PartImpl {}
    /** Class for TA5 transmission parts */
    public final class TA5 extends PartImpl {}
    /** Class for TS6 transmission parts */
    public final class TS6 extends PartImpl {}
    /** Class for TSF7 transmission parts */
    public final class TSF7 extends PartImpl {}
    /** Class for TC120 transmission parts */
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

    /**
     * Returns a set of transmission part types.
     * @return Set of transmission part types
     */
    public Set<PartType> getTransPartTypes(){
        return  transPartTypes ;
    }

    // Exterior classes
    /** Class for XC exterior parts */
    public final class XC extends PartImpl {}
    /** Class for XM exterior parts */
    public final class XM extends PartImpl {}
    /** class for XS exterior parts */
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

    /**
     * Returns a set of exterior part types.
     * @return Set of exterior part types
     */
    public Set<PartType> getExPartTypes(){
        return  exPartTypes ;
    }

    // Interior classes
    /** Class for IN interior parts */
    public final class IN extends PartImpl {}
    /** Class for IH interior parts */
    public final class IH extends PartImpl {}
    /** Class for IS interior parts */
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

    /**
     * Returns a set of interior part types.
     * @return Set of interior part types
     */
    public Set<PartType> getInPartTypes(){
        return  inPartTypes ;
    }

    private Map<Category, Set<PartType>> variants = new HashMap<>(){{
        put(engine,engPartTypes);
        put(transmission,transPartTypes);
        put(exterior,exPartTypes);
        put(interior,inPartTypes);
    }};

    /**
     * Returns a map associating categories to their available part types.
     * @return Map associating categories to a set of their available part types
     * @see ConfiguratorImpl#ConfiguratorImpl(Map)
     */
    @Override
    public Map<Category, Set<PartType>> getCatalog() {
        return variants;
    }

    // incompatibilities
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
    // incompatibilities set
    private HashMap<PartType, Set<PartType>> incompatibilities = new HashMap<>(){{
        put(xs,incForXs);
        put(is,incForIs);
        put(ta5,incForTa5);
        put(tsf7,incForTsf7);
        put(xc,incForXc);
        put(xm,incForXm);
    }};

    /**
     * Returns a map associating part types to their incompatible part types.
     * @return Map associating part types to a set of their incompatible part
     *         types
     */
    public HashMap<PartType, Set<PartType>> getIncompatibilities() {
        return incompatibilities;
    }

    // requirements
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

    // set requirements
    private HashMap<PartType, Set<PartType>> requirements = new HashMap<>(){{
        put(eh120,reqForEh120);
        put(tc120,reqForTc120);
        put(xs,reqForXs);
        put(is,reqForIs);
    }};

    /**
     * Returns a map associating part types to their required part types.
     * @return Map associating part types to a set of their required part types
     */
    public HashMap<PartType, Set<PartType>> getRequirements() {
        return requirements;
    }

    /**
     * Returns a category of given name.
     * @param categoryName Name of the category
     * @return Category of given name, or <code>null</code> if the category does
     *         not exist.
     */
    public Category accessToCategory (String categoryName){
        CategoryImpl category = new CategoryImpl(categoryName);
        if (categories.contains(category)) return  category ;
        else return  null ;
    }

    /**
     * Returns a part type of given name.
     * @param partTypeName Name of the part type
     * @return Part type of given name, or <code>null</code> if the part type
     *         does not exist.
     */
    public PartType accessToPartType (String partTypeName){
        for (Category category : categories){
            Set<PartType> variants1 = this.variants.get(category);
            for (PartType part : variants1){
                if (part.getName().equals(partTypeName)) return part;
            }
        }
        return  null ;
    }

    /**
     * Initialize given compatibility manager with incompatibilities and
     * requirements.
     * @param compatibilityManager compatibility manager to initialize
     */
    @Override
    public void
    initCompatibilityManager(CompatibilityManager compatibilityManager) {
        for(PartType ref: incompatibilities.keySet())
            compatibilityManager.addIncompatibilities(
                    ref,
                    incompatibilities.get(ref)
            );
        for(PartType ref: requirements.keySet())
            compatibilityManager.addRequirements(ref, requirements.get(ref));
    }
}
