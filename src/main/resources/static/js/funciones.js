
$(document).ready(function(){
	$('#pestana2').on('click', function(){
		cargarListasDeTareasAjax();
	});
});

function cargarListasDeTareasAjax(){
	$.ajax({
		type: 'GET',
		url: '/tareasRest/getListasTareasUsuario',
		success: function(respuesta){
			$('#listasTareasTabla tbody').empty();
			respuesta.forEach(listaTareas =>{
				$('#listasTareasTabla tbody').append(
					`<tr>
						<td>${listaTareas.nombre}</td>
						<td>${listaTareas.descripcion}</td>
						<td class="acciones centrado">
							<div class="botonesAcciones">
								<form action="/entrarListaTareas" method="get">
									<input type="hidden" name="idLista" value="${listaTareas.id}">
									<button type="submit" id="botonEntrarListaTareas${listaTareas.id}">Entrar</button>
								</form>
								<form action="/formularioEditarListaTareas" method="get">
									<input type="hidden" name="idLista" value="${listaTareas.id}">
									<button type="submit" id="botonEditarListaTareas${listaTareas.id}">Editar</button>
								</form>
								<button id="botonEliminarListaTareas${listaTareas.id}" onclick="eliminarListaTareasAjax(${listaTareas.id})">Eliminar</button>
							</div>
						</td>
					</tr>`
				)
			});
		},
		error: function(error){
			console.error('Error al obtener las listas de tareas: ', error);
		}
	});
}

function eliminarListaTareasAjax(idListaTarea){
	$.ajax({
		type: 'GET',
		url: '/tareasRest/eliminarListaTareas',
		data: {'idListaTarea': idListaTarea},
		success: function (respuesta) { 
			console.log('Se ha realizado el borrado de la lista de tareas correctamente.');
			cargarListasDeTareasAjax();
		 },
		error: function(error){
			log.error('Ha ocurrido un error al eliminar la lista de tareas: ', error);
		}
	});
}




function cambiarPestana(pestanaId){
	var pestanas = document.getElementsByClassName('pestana')
	for (var i = 0; i < pestanas.length; i++){
		pestanas[i].classList.remove('activo');
	}
	
	var pestanasContenido = document.getElementsByClassName('pestanaContenido');
	for(var i = 0; i < pestanasContenido.length; i++){
		pestanasContenido[i].classList.remove('activo');
	}
	
	var pestanaPulsada = document.getElementById(pestanaId);
	pestanaPulsada.classList.add('activo');
	
	var contenidoPestanaPulsada = document.getElementById(pestanaId + 'Contenido');
	contenidoPestanaPulsada.classList.add('activo');
}