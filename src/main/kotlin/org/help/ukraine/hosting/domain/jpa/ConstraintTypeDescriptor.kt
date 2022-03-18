package org.help.ukraine.hosting.domain.jpa

import org.help.ukraine.hosting.domain.model.Constraint
import org.help.ukraine.hosting.domain.model.PetAllowedConstraint
import org.hibernate.type.descriptor.WrapperOptions
import org.hibernate.type.descriptor.java.AbstractTypeDescriptor

abstract class ConstraintTypeDescriptor<T: Constraint>(clazz: Class<T>) : AbstractTypeDescriptor<T>(clazz) {

    override fun <X : Any?> unwrap(value: T, type: Class<X>?, options: WrapperOptions?): X {
        return type
            ?.isAssignableFrom(String::class.java)
            .let { value.javaClass.name as X }
            ?: run { throw RuntimeException("Unable to unwrap $value of type $type with options $options") }
    }
}
//
//class PetAllowed: ConstraintTypeDescriptor<PetAllowedConstraint>() {
//    override fun fromString(string: String?): PetAllowedConstraint {
//        TODO("Not yet implemented")
//    }
//
//    override fun <X : Any?> wrap(value: X, options: WrapperOptions?): PetAllowedConstraint {
//        TODO("Not yet implemented")
//    }
//}
