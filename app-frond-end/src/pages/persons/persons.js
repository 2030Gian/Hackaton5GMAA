import { useEffect, useState } from "react"
import { fetchGroups, fetchPersons } from "../../api/dataService"
import 'devextreme/data/odata/store';
import DataGrid, { Column } from 'devextreme-react/data-grid';

export default function Group() {
  // Se declara un estado llamado 'groupsWithPersonCount' utilizando useState.
  const [groupsWithPersonCount, setGroupsWithPersonCount] = useState();

  // Utiliza useEffect para realizar la solicitud a la API cuando el componente se monta.
  useEffect(() => {
    fetchPersons()
      .then((response) => {
        // Extrae los datos de respuesta de la solicitud.
        const personsData = response.data;

        // Registra los datos de grupos en la consola.
        console.log(personsData);

        // Modifica el estado 'groupsWithPersonCount' transformando los datos de grupos.
        setGroupsWithPersonCount(personsData.map(group => ({
          id: group.id,
          name: group.name  
        })))
      })
      .catch((error) => {
        console.log(error);
      })
  }, []); // El [] como segundo argumento asegura que useEffect se ejecute solo una vez al montar el componente.

  return (
      <DataGrid
        // Establece la fuente de datos para el DataGrid como 'groupsWithPersonCount'.
        dataSource={groupsWithPersonCount}
        
        // Muestra bordes alrededor de las celdas de la tabla.
        showBorders={true}
      >
        {/* Define la primera columna con el campo 'id' y un ancho de 50 píxeles. */}
        <Column dataField="id" width={50} />
        
        {/* Define la segunda columna con el campo 'name'. */}
        <Column dataField="name" />
      </DataGrid>

  )
}