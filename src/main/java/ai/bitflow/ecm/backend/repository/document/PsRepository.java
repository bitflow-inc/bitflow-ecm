package ai.bitflow.ecm.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ai.bitflow.ecm.backend.domain.TbFileTreeMap;

public interface PsRepository extends JpaRepository<TbFileTreeMap, Integer> {


}
