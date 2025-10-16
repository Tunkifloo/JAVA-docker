-- Create database if not exists (this runs automatically via POSTGRES_DB env var)
-- CREATE DATABASE employeesdb;

-- Connect to the database
\c employeesdb;

-- Create employees table (JPA will create it, but this is a fallback)
CREATE TABLE IF NOT EXISTS employees (
                                         id BIGSERIAL PRIMARY KEY,
                                         first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    email VARCHAR(150) NOT NULL UNIQUE,
    phone VARCHAR(20),
    position VARCHAR(100),
    department VARCHAR(50),
    hire_date DATE,
    salary DECIMAL(10, 2),
    is_active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    );

-- Create indexes for better performance
CREATE INDEX IF NOT EXISTS idx_employees_department ON employees(department);
CREATE INDEX IF NOT EXISTS idx_employees_email ON employees(email);
CREATE INDEX IF NOT EXISTS idx_employees_is_active ON employees(is_active);

-- Insert sample data
INSERT INTO employees (first_name, last_name, email, phone, position, department, hire_date, salary, is_active)
VALUES
    ('Juan', 'Pérez', 'juan.perez@company.com', '+51-987654321', 'Software Engineer', 'Engineering', '2023-01-15', 4500.00, true),
    ('María', 'García', 'maria.garcia@company.com', '+51-987654322', 'Product Manager', 'Product', '2022-06-20', 5500.00, true),
    ('Carlos', 'López', 'carlos.lopez@company.com', '+51-987654323', 'DevOps Engineer', 'Engineering', '2023-03-10', 5000.00, true),
    ('Ana', 'Rodríguez', 'ana.rodriguez@company.com', '+51-987654324', 'UX Designer', 'Design', '2022-11-05', 4200.00, true),
    ('Luis', 'Martínez', 'luis.martinez@company.com', '+51-987654325', 'QA Engineer', 'Engineering', '2023-05-22', 3800.00, true),
    ('Carmen', 'Fernández', 'carmen.fernandez@company.com', '+51-987654326', 'HR Manager', 'Human Resources', '2021-08-15', 5200.00, true),
    ('Diego', 'Torres', 'diego.torres@company.com', '+51-987654327', 'Frontend Developer', 'Engineering', '2023-07-01', 4300.00, true),
    ('Sofia', 'Ramos', 'sofia.ramos@company.com', '+51-987654328', 'Backend Developer', 'Engineering', '2023-02-18', 4700.00, true),
    ('Miguel', 'Cruz', 'miguel.cruz@company.com', '+51-987654329', 'Data Analyst', 'Analytics', '2022-12-10', 4100.00, true),
    ('Laura', 'Vargas', 'laura.vargas@company.com', '+51-987654330', 'Marketing Manager', 'Marketing', '2022-04-25', 5000.00, false)
    ON CONFLICT (email) DO NOTHING;

-- Create a function to update updated_at timestamp
CREATE OR REPLACE FUNCTION update_updated_at_column()
RETURNS TRIGGER AS $$
BEGIN
    NEW.updated_at = CURRENT_TIMESTAMP;
RETURN NEW;
END;
$$ language 'plpgsql';

-- Create trigger for updated_at
DROP TRIGGER IF EXISTS update_employees_updated_at ON employees;
CREATE TRIGGER update_employees_updated_at
    BEFORE UPDATE ON employees
    FOR EACH ROW
    EXECUTE FUNCTION update_updated_at_column();

-- Print success message
DO $$
BEGIN
    RAISE NOTICE 'Database initialized successfully!';
END $$;