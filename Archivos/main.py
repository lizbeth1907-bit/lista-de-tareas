import tkinter as tk
from tkinter import messagebox

# ---------------------------
# MODELO: Datos del inventario
# ---------------------------
inventario = {}

# ---------------------------
# CONTROLADOR: Funciones CRUD
# ---------------------------
def agregar_producto():
    codigo = entry_codigo.get()
    nombre = entry_nombre.get()
    cantidad = entry_cantidad.get()

    if codigo == "" or nombre == "" or cantidad == "":
        messagebox.showwarning("Error", "Todos los campos son obligatorios")
        return

    if codigo in inventario:
        messagebox.showwarning("Error", "El código ya existe")
    else:
        inventario[codigo] = {"nombre": nombre, "cantidad": int(cantidad)}
        messagebox.showinfo("Éxito", f"Producto '{nombre}' agregado")
        limpiar_campos()

def consultar_producto():
    codigo = entry_codigo.get()
    if codigo in inventario:
        producto = inventario[codigo]
        messagebox.showinfo("Consulta",
                            f"Código: {codigo}\n"
                            f"Nombre: {producto['nombre']}\n"
                            f"Cantidad: {producto['cantidad']}")
    else:
        messagebox.showwarning("Error", "Producto no encontrado")

def actualizar_producto():
    codigo = entry_codigo.get()
    if codigo in inventario:
        nuevo_nombre = entry_nombre.get()
        nueva_cantidad = entry_cantidad.get()
        inventario[codigo] = {"nombre": nuevo_nombre, "cantidad": int(nueva_cantidad)}
        messagebox.showinfo("Éxito", f"Producto '{codigo}' actualizado")
        limpiar_campos()
    else:
        messagebox.showwarning("Error", "Producto no encontrado")

def eliminar_producto():
    codigo = entry_codigo.get()
    if codigo in inventario:
        inventario.pop(codigo)
        messagebox.showinfo("Éxito", f"Producto '{codigo}' eliminado")
        limpiar_campos()
    else:
        messagebox.showwarning("Error", "Producto no encontrado")

def limpiar_campos():
    entry_codigo.delete(0, tk.END)
    entry_nombre.delete(0, tk.END)
    entry_cantidad.delete(0, tk.END)

# ---------------------------
# VISTA: Interfaz gráfica
# ---------------------------
ventana = tk.Tk()
ventana.title("Gestión de Inventario - Supermercado")
ventana.geometry("400x300")

# Etiquetas y entradas
tk.Label(ventana, text="Código:").pack()
entry_codigo = tk.Entry(ventana)
entry_codigo.pack()

tk.Label(ventana, text="Nombre:").pack()
entry_nombre = tk.Entry(ventana)
entry_nombre.pack()

tk.Label(ventana, text="Cantidad:").pack()
entry_cantidad = tk.Entry(ventana)
entry_cantidad.pack()

# Botones
tk.Button(ventana, text="Agregar", command=agregar_producto).pack(pady=2)
tk.Button(ventana, text="Consultar", command=consultar_producto).pack(pady=2)
tk.Button(ventana, text="Actualizar", command=actualizar_producto).pack(pady=2)
tk.Button(ventana, text="Eliminar", command=eliminar_producto).pack(pady=2)
tk.Button(ventana, text="Limpiar", command=limpiar_campos).pack(pady=2)

ventana.mainloop()