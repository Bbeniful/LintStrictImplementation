package com.bbeniful.linterem

import com.bbeniful.strict_implementation.annotation_processor.AllowedImplementation
import com.bbeniful.strict_implementation.annotation_processor.MyInterface
import com.bbeniful.strict_implementation.annotation_processor.RequiresMyInterface

@RequiresMyInterface
class TestClass: MyInterface {
    override fun getName(): String {
        TODO("Not yet implemented")
    }
}

@AllowedImplementation
@RequiresMyInterface
class TestClass2: MyInterface {
    override fun getName(): String {
        return ""
    }
}