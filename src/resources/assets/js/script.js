// Obtener referencias a los elementos HTML
const agregarProductoBtn = document.getElementById('agregarProducto');
const nuevaVentaForm = document.getElementById('nuevaVentaForm');
const agregarProveedorBtn = document.getElementById('agregarProveedor');

// Evento clic para agregar un producto
agregarProductoBtn.addEventListener('click', function() {
  // Aquí se podría mostrar un formulario para agregar un nuevo producto
  // o realizar alguna acción específica
  console.log('Agregar producto');
});

// Evento submit para registrar una venta
nuevaVentaForm.addEventListener('submit', function(event) {
  event.preventDefault();
  // Aquí se podrían obtener los valores del formulario
  // y enviar una solicitud a la base de datos para registrar la venta
  console.log('Venta registrada');
  // Luego, podrías actualizar la tabla de ventas para mostrar la nueva venta
  // o realizar otras acciones necesarias
});

// Evento clic para agregar un proveedor
agregarProveedorBtn.addEventListener('click', function() {
  // Aquí se podría mostrar un formulario para agregar un nuevo proveedor
  // o realizar alguna acción específica
  console.log('Agregar proveedor');
});