package fr.eni.projecteni1.service;

import fr.eni.projecteni1.bo.Type;
import fr.eni.projecteni1.repository.TypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeServiceImpl implements TypeService {
    private TypeRepository typeRepo;

    public TypeServiceImpl(TypeRepository typeRepo) {
        this.typeRepo = typeRepo;
    }

    public List<Type> getTypes() {
        return this.typeRepo.getTypes();
    }
}
