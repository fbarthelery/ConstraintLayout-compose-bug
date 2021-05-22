ConstraintLayout compose bug in constraintlayout-compose:1.0.0-alpha07
============
# ConstraintLayout-compose-bug
Sample project for ConstraintLayout-compose-bug in constraintlayout-compose:1.0.0-alpha07

Description
----------
In constraintlayout-compose:1.0.0-alpha07, ConstraintLayout with decoupled constraints is not recomposed when constraints change.

I think it is because [rememberConstraintLayoutMeasurePolicy()](https://cs.android.com/androidx/constraintlayout/+/main:constraintlayout/compose/src/main/java/androidx/constraintlayout/compose/ConstraintLayout.kt;l=100)  should pass its parameters as keys to the `remember()`  call.
A workaround for now is to wrap ConstraintLayout into a `key(constraints){ ... }` call
