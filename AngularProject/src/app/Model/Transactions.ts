export class Transactions {

    reference_number: string;
    total_compra: string;
    direccion_compra:string;
    estado:string;
    fecha:string;
    pan:string;

    constructor( reference_number: string,    total_compra: string, direccion_compra:string,estado:string,fecha:string,pan:string){
        this.reference_number=reference_number;
        this.total_compra=total_compra;
        this.direccion_compra=direccion_compra;
        this.estado=estado;
        this.fecha=fecha;
        this.pan=pan;
    }

    
}