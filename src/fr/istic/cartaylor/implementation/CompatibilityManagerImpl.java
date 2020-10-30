package fr.istic.cartaylor.implementation;

import fr.istic.cartaylor.api.CompatibilityManager;
import fr.istic.cartaylor.api.PartType;

import java.util.Collections;
import java.util.HashMap;
import java.util.Set;

/**
 * @author Arnaud Akoto <yao-arnaud.akoto@etudiant.univ-rennes1.fr>
 * @author Anthony Amiard <anthony.amiard@etudiant.univ-rennes1.fr>
 *        Classe Implementant l'interface CompatibilityManager.
 */
public class CompatibilityManagerImpl implements CompatibilityManager {
    private HashMap<PartType, Set<PartType>> incompatibilities;
    private HashMap<PartType, Set<PartType>> requirements;

    public CompatibilityManagerImpl(Initiations initiations1){
        incompatibilities = initiations1.getIncompatibilities();
        requirements = initiations1.getRequirements();

    }
    /**
     * Add a set of incompatible part types to given part type.
     * The incompatibility is set in both direction for each reference-target association.
     *
     * @param reference A part type
     * @param target    All incompatible part types with <code>reference</code>
     */
    @Override
    public void addIncompatibilities(PartType reference, Set<PartType> target) throws CarTaylorExceptions {
        if (reference !=null){
            if (target !=null){
                Set<PartType> referenceReq = requirements.get(reference);
                Set<PartType> referenceInc =incompatibilities.get(reference);
                if (referenceInc.equals(target)){
                   throw new CarTaylorExceptions("l'ensemble existe deja dans les incompatibilités de "+reference.getName()+".");
                }
                for (PartType part:target ){
                    if (referenceReq.contains(part) || requirements.get(part).contains(reference)) {
                        throw new CarTaylorExceptions("conflit: "+part.getName()+" est un requirement.");
                    }

                    if (incompatibilities.get(part).contains(reference)) target.remove(part);
                }

                if (!target.isEmpty()){
                    if (incompatibilities.containsKey(reference)){
                        referenceInc.addAll(target);
                    }else {
                        incompatibilities.put(reference,target);
                    }
                }
            }
        }
    }

    /**
     * Remove an incompatibility between given part types.
     *
     * @param reference First part type
     * @param target    Second part type
     */
    @Override
    public void removeIncompatibility(PartType reference, PartType target) {
        if (reference != null){
            if (target != null){
                Set<PartType> referenceInc =incompatibilities.get(reference);
                if (referenceInc.contains(target)) {
                    referenceInc.remove(target);
                    if (referenceInc.isEmpty()) incompatibilities.remove(reference);
                }
                else  {
                    incompatibilities.get(target).remove(reference);
                    if ( incompatibilities.get(target).isEmpty()) incompatibilities.remove(target);
                }
            }
        }
    }

    /**
     * Add a set of required part types to use with given part type.
     * This means that <code>reference</code> needs all part types into <code>target</code> to be used.
     * It does not specify if each part type into <code>target</code> needs <code>reference</code> to be used, or not.
     *
     * @param reference Part type to use
     * @param target    All required part types
     */
    @Override
    public void addRequirements(PartType reference, Set<PartType> target) throws CarTaylorExceptions {
        if (reference != null){
            if (target != null){
                Set<PartType> referenceReq = requirements.get(reference);
                if (referenceReq.equals(target)){
                    throw new CarTaylorExceptions("l'ensemble existe deja dans les requirements de "+reference.getName()+".");
                }
                Set<PartType> referenceInc =incompatibilities.get(reference) ;
                for (PartType part:target ){
                    if (referenceInc.contains(part) || incompatibilities.get(part).contains(reference)){
                        throw new CarTaylorExceptions("conflit: "+part.getName()+" est incompatible avec "+ reference.getName() +".");
                    }

                }
                if (requirements.containsKey(reference)){
                    referenceReq.addAll(target);
                }else {
                    requirements.put(reference,target);
                }
            }
        }
    }

    /**
     * Remove a required part type for a given part type.
     * This means that <code>reference</code> does not longer needs <code>target</code> to be used.
     * It does not specify if <code>target</code> needs <code>reference</code> to be used, or not.
     *
     * @param reference Part type to use
     * @param target    No longer required part type
     */
    @Override
    public void removeRequirement(PartType reference, PartType target) {
        if (reference != null){
            if (target != null){
                Set<PartType> referenceReq =requirements.get(reference);
                if (referenceReq.contains(target))  {
                    referenceReq.remove(target);
                    if (referenceReq.isEmpty()) requirements.remove(reference);
                }
            }
        }
    }

    /**
     * Returns a set of part types that are incompatible with given part type.
     *
     * @param reference Part type to check
     * @return Set of incompatible part types (immutable)
     */
    @Override
    public Set<PartType> getIncompatibilities(PartType reference) {
        return (incompatibilities.containsKey(reference))
                ? Collections.unmodifiableSet(incompatibilities.get(reference))
                : null ;
    }

    /**
     * Returns a set of part type required to use given part type.
     *
     * @param reference Par type to check
     * @return Set of required part types (immutable)
     */
    @Override
    public Set<PartType> getRequirements(PartType reference) {
        return (requirements.containsKey(reference))
                ? Collections.unmodifiableSet(requirements.get(reference))
                : null ;
    }
}
