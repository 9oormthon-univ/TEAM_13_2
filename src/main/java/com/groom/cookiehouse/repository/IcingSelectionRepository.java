package com.groom.cookiehouse.repository;

import com.groom.cookiehouse.domain.icing.IcingSelection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IcingSelectionRepository extends JpaRepository<IcingSelection, Long> {
}
