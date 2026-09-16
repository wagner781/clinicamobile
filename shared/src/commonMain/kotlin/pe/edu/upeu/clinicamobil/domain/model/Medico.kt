package pe.edu.upeu.clinicamobil.domain.model

data class Medico(
    val id: Long,
    val nombre: String,
    val colegiatura: String,
    val especialidad: String?,
) {
    init {
        require(nombre.isNotBlank()) { "El nombre es obligatorio" }
        require(colegiatura.isNotBlank()) { "La colegiatura es obligatoria" }
        require(especialidad == null || especialidad.isNotBlank()) {
            "La especialidad no puede ser vacía"
        }
    }
}