package com.example.composetask.home.data.repositoryimb

import com.example.composetask.core.presentation.base.BaseState
import com.example.composetask.home.data.responseremote.AssociatedDrug
import com.example.composetask.home.data.responseremote.Asthma
import com.example.composetask.home.data.responseremote.ClassNamer
import com.example.composetask.home.data.responseremote.Diabete
import com.example.composetask.home.data.responseremote.Lab
import com.example.composetask.home.data.responseremote.Medication
import com.example.composetask.home.data.responseremote.MedicationsClasse
import com.example.composetask.home.data.responseremote.ModelGetMedicinesResponseRemote
import com.example.composetask.home.data.responseremote.Problem
import com.example.composetask.home.domain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class FakeHomeRepositoryImpl : HomeRepository {

    override suspend fun getMedicines(): Flow<BaseState<ModelGetMedicinesResponseRemote>> {
        return flow {
            val asthma1 = Asthma(id = null, nothing = "Asthma Medication 1")
            val asthma2 = Asthma(id = null, nothing = "Asthma Medication 2")
            val diabetes1 = Diabete(
                labs = listOf(Lab(missing_field = "Lab Test 1")),
                medications = listOf(
                    Medication(
                        medicationsClasses = listOf(
                            MedicationsClasse(
                                className = listOf(
                                    ClassNamer(
                                        associatedDrug = listOf(
                                            AssociatedDrug(
                                                dose = "10 mg",
                                                name = "Drug 1",
                                                strength = "100 mg"
                                            )
                                        ),
                                        associatedDrug2 = listOf(
                                            AssociatedDrug(
                                                dose = "20 mg",
                                                name = "Drug 2",
                                                strength = "200 mg"
                                            )
                                        )
                                    )
                                ),
                                className2 = listOf(
                                    ClassNamer(
                                        associatedDrug = listOf(
                                            AssociatedDrug(
                                                dose = "30 mg",
                                                name = "Drug 3",
                                                strength = "300 mg"
                                            )
                                        ),
                                        associatedDrug2 = listOf(
                                            AssociatedDrug(
                                                dose = "40 mg",
                                                name = "Drug 4",
                                                strength = "400 mg"
                                            )
                                        )
                                    )
                                )
                            )
                        )
                    )
                )
            )
            val diabetes2 = Diabete(
                labs = listOf(Lab(missing_field = "Lab Test 2")),
                medications = listOf(
                    Medication(
                        medicationsClasses = listOf(
                            MedicationsClasse(
                                className = listOf(
                                    ClassNamer(
                                        associatedDrug = listOf(
                                            AssociatedDrug(
                                                dose = "50 mg",
                                                name = "Drug 5",
                                                strength = "500 mg"
                                            )
                                        ),
                                        associatedDrug2 = listOf(
                                            AssociatedDrug(
                                                dose = "60 mg",
                                                name = "Drug 6",
                                                strength = "600 mg"
                                            )
                                        )
                                    )
                                ),
                                className2 = listOf(
                                    ClassNamer(
                                        associatedDrug = listOf(
                                            AssociatedDrug(
                                                dose = "70 mg",
                                                name = "Drug 7",
                                                strength = "700 mg"
                                            )
                                        ),
                                        associatedDrug2 = listOf(
                                            AssociatedDrug(
                                                dose = "80 mg",
                                                name = "Drug 8",
                                                strength = "800 mg"
                                            )
                                        )
                                    )
                                )
                            )
                        )
                    )
                )
            )
            val problem1 = Problem(id = null, Asthma = listOf(asthma1, asthma2), Diabetes = listOf(diabetes1, diabetes2))
            val problem2 = Problem(id = null, Asthma = listOf(asthma2, asthma1), Diabetes = listOf(diabetes2, diabetes1))

            val model = ModelGetMedicinesResponseRemote(
                id = null,
                problems = listOf(problem1, problem2)
            )


            // Emit the fake response as a BaseState.Success
            emit(BaseState.Success(model))
        }
    }


}